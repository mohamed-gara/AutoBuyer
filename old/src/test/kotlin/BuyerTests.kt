import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

class BuyerTests {
    @Test
    fun closes_when_item_closes() {
        val listener = mock<IBuyerListener>()
        val sut = Buyer("ItemId", 10, 1, null)
        sut.addBuyerListener(listener)

        sut.itemClosed()

        verify(listener).buyerStateChanged(BuyerSnapshot("ItemId", 0, 0, 0, BuyerState.Closed))
    }

    @Test
    fun buyer_does_not_buy_when_price_event_with_too_high_price_arrives() {
        val mock = mock<IBuyerListener>()
        val sut = Buyer("ItemId", 10, 1, null)
        sut.addBuyerListener(mock)

        sut.currentPrice(20, 5)

        verify(mock).buyerStateChanged(BuyerSnapshot("ItemId", 20, 5, 0, BuyerState.Monitoring))
    }

    @Test
    fun buyer_buys_when_price_event_with_appropriate_price_arrives() {
        val listener = mock<IBuyerListener>()
        val stockItem = mock<IStockItem>()
        val sut = Buyer("ItemId", 50, 1, stockItem)
        sut.addBuyerListener(listener)

        sut.currentPrice(10, 5)

        verify(listener).buyerStateChanged(BuyerSnapshot("ItemId", 10, 5, 0, BuyerState.Buying))
        verify(stockItem).buy(10, 1)
    }

    @Test
    fun buyer_attempts_to_buy_maximum_amount_available() {
        val stockItem = mock<IStockItem>()
        val sut = Buyer("ItemId", 50, 10, stockItem)

        sut.currentPrice(10, 5)

        verify(stockItem).buy(10, 5)
    }

    @Test fun buyer_does_not_react_to_a_purchase_event_related_to_another_buyer() {
        val stockItem = mock<IStockItem>()
        val sut = Buyer("ItemId", 10, 1, stockItem)
        sut.currentPrice(100, 5)

        sut.itemPurchased(1, PurchaseSource.FromOtherBuyer)
    }

    @Test
    fun buyer_updates_items_bought_so_far_when_purchase_event_with_the_same_user_name_arrives() {
        val listener = mock<IBuyerListener>()
        val sut = Buyer("ItemId", 10, 10, null)
        sut.addBuyerListener(listener)
        sut.currentPrice(50, 10)

        sut.itemPurchased(1, PurchaseSource.FromBuyer)

        verify(listener).buyerStateChanged(BuyerSnapshot("ItemId", 50, 9, 1, BuyerState.Monitoring))
    }

    @Test fun buyer_closes_when_it_buys_enough_items() {
        val listener = mock<IBuyerListener>()
        val sut = Buyer("ItemId", 10, 5, null)
        sut.addBuyerListener(listener)
        sut.currentPrice(50, 10)

        sut.itemPurchased(5, PurchaseSource.FromBuyer)

        verify(listener).buyerStateChanged(BuyerSnapshot("ItemId", 50, 5, 5, BuyerState.Closed))
    }

    @Test fun closed_buyer_does_not_react_to_further_messages_() {
        val listener = mock<IBuyerListener>()
        val stockItem = mock<IStockItem>()
        val sut = Buyer("ItemId", 10, 10, stockItem)
        sut.addBuyerListener(listener)
        sut.itemClosed()

        sut.currentPrice(10, 10)

        verify(listener).buyerStateChanged(BuyerSnapshot("ItemId", 0, 0, 0, BuyerState.Closed))
    }
}
