class StockItemConnection : IStockItemConnection {
    override var buyerName: String
        get() {
            return buyerName
        }

    constructor(buyerName: String) {
        this.buyerName = buyerName;
    }

    override fun messageReceived(): (String) -> Unit {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendMessage(message: String) {
        // Send the message through a 3rd party SDK
    }
}
