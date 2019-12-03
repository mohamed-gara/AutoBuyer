class WarehouseConnection : IWarehouseConnection {
    private val _connections = arrayListOf<StockItemConnection>();

    override fun connectToItem(itemId: String, buyerName: String): IStockItemConnection {
        var connection = StockItemConnection(buyerName);
        _connections.add(connection);

        return connection;
    }
}
