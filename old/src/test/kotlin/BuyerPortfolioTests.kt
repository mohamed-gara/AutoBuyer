import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

class BuyerPortfolioTests {
    @Test
    fun notifies_listeners_of_new_buyers() {
        var listener = mock<IPortfolioListener>()
        var sut = BuyerPortfolio()
        sut.addPortfolioListener(listener)

        var buyer = Buyer("ItemId", 10, 1, null)
        sut.addBuyer(buyer);

        verify(listener).buyerAdded(buyer)
    }
}
