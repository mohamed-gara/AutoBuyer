import assertk.assertThat
import assertk.assertions.hasClass
import assertk.assertions.hasMessage
import assertk.assertions.isFailure
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class StockMessageTranslatorTests {
    @Test
    fun notifies_stock_closes_when_close_message_received() {
        var sut = StockMessageTranslator ("Buyer");
        var mock = mock<IStockEventListener>();
        sut.addStockEventListener(mock);

        sut.processMessage("Event: CLOSE;");

        verify(mock).itemClosed ()
    }

    @Test
    fun notifies_current_price_when_price_message_received() {
        var sut = StockMessageTranslator ("Buyer");
        var mock = mock<IStockEventListener>();
        sut.addStockEventListener(mock);

        sut.processMessage("Event: PRICE; CurrentPrice: 12; NumberInStock: 34");

        verify(mock).currentPrice(12, 34)
    }

    @Test
    fun notifies_item_purchased_by_the_buyer_when_purchase_message_received() {
        var sut = StockMessageTranslator ("Buyer");
        var mock = mock<IStockEventListener>();
        sut.addStockEventListener(mock);

        sut.processMessage("Event: PURCHASE; BuyerName: Buyer; NumberSold: 1");

        verify(mock).itemPurchased (1, PurchaseSource.FromBuyer)
    }

    @Test
    fun notifies_item_purchased_by_other_buyer_when_purchase_message_received() {
        var sut = StockMessageTranslator ("Buyer");
        var mock = mock<IStockEventListener>();
        sut.addStockEventListener(mock);

        sut.processMessage("Event: PURCHASE; BuyerName: OtherBuyer; NumberSold: 1");

        verify(mock).itemPurchased (1, PurchaseSource.FromOtherBuyer)
    }

    @Test
    fun throws_when_incorrect_message_received() {
        var sut = StockMessageTranslator ("Buyer");
        var mock = mock<IStockEventListener>();
        sut.addStockEventListener(mock);

        assertThat {
            sut.processMessage("incorrect message")
        }
        .isFailure().hasClass(IllegalArgumentException::class)
    }

    @Test
    fun throws_when_message_of_unknown_type_received() {
        var sut = StockMessageTranslator ("Buyer");
        var mock = mock<IStockEventListener>();
        sut.addStockEventListener(mock);

        assertThat {
            sut.processMessage("Event: UNKNOWN;")
        }
        .isFailure().hasClass(IllegalArgumentException::class)
    }
}
