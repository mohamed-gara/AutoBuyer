import org.junit.jupiter.api.Test

class BuyerPortfolioTests {
    @Test
    fun notifies_listeners_of_new_buyers() {
        var sut = BuyerPortfolio();
        var mock = Mock<IPortfolioListener>();
        sut.AddPortfolioListener(mock.Object);
        var buyer = Buyer("ItemId", 10, 1, null);

        sut.addBuyer(buyer);

        mock.Verify(x => x.BuyerAdded(buyer));
    }
}
