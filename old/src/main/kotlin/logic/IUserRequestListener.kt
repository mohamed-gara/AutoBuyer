interface IUserRequestListener {
    fun startBuying(newItemId: String, newItemMaximumPrice: Int, numberToBuy: Int)
}
