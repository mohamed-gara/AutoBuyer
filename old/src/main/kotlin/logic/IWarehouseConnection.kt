interface IWarehouseConnection {
    fun connectToItem(itemId: String, buyerName: String): IStockItemConnection
}
