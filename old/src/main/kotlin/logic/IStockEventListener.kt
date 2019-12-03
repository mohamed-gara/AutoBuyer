interface IStockEventListener {
    fun currentPrice( price:Int,  numberInStock: Int)
    fun itemPurchased(numberSold:Int, purchaseSource:PurchaseSource)
    fun itemClosed()
}

enum class PurchaseSource {
    FromBuyer,
    FromOtherBuyer
}
