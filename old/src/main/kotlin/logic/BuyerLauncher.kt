class BuyerLauncher : IUserRequestListener {

    private val warehouse: IWarehouse
    private val portfolio: IBuyerPortfolio

    constructor(warehouse: IWarehouse, portfolio: IBuyerPortfolio) {
        this.warehouse = warehouse
        this.portfolio = portfolio
    }

    override fun startBuying(newItemId: String, newItemMaximumPrice: Int, numberToBuy: Int) {
        val stockItem = warehouse.getStockItemFor(newItemId)
        var buyer = Buyer (newItemId, newItemMaximumPrice, numberToBuy, stockItem)
        stockItem.addStockEventListener(buyer)
        portfolio.addBuyer(buyer)
    }
}
