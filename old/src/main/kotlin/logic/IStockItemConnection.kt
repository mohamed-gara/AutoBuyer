interface IStockItemConnection {
    fun messageReceived(): (String) -> Unit;
    val buyerName: String
    fun sendMessage(message: String);
}

