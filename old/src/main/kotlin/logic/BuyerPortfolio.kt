class BuyerPortfolio : IBuyerPortfolio {

    private val listeners = arrayListOf<IPortfolioListener>();
    private val buyers = arrayListOf<Buyer>();

    override fun addBuyer(buyer: Buyer) {
        buyers.add(buyer);
        for (listener in listeners) {
            listener.buyerAdded(buyer);
        }
    }

    fun addPortfolioListener(listener: IPortfolioListener) {
        listeners.add(listener);
    }
}

