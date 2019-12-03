interface IStockItem {
    fun buy(price: Int, numberToBuy: Int)
    fun addStockEventListener(listener: IStockEventListener)
}

