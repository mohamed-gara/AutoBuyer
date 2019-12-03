class Warehouse : IWarehouse {
    private val connection: IWarehouseConnection
    private val buyerName: String

    constructor(connection: IWarehouseConnection, buyerName: String) {
        this.buyerName = buyerName;
        this.connection = connection;
    }

    override fun getStockItemFor(itemId: String): IStockItem {
        return StockItem(connection.connectToItem(itemId, buyerName));
    }
}
