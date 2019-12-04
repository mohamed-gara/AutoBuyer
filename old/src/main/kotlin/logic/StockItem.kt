class StockItem : IStockItem {
    private val buyCommandFormat = "Command: BUY; Price: {0}; Number: {1}";

    private val connection: IStockItemConnection
    private val translator: StockMessageTranslator

    constructor(connection: IStockItemConnection) {
        this.connection = connection;
        translator = StockMessageTranslator(connection.buyerName);
        //connection.messageReceived(TranslateMessage);
    }

    override fun buy(price: Int, numberToBuy: Int) {
        connection.sendMessage(String.format(buyCommandFormat, price, numberToBuy));
    }

    private fun translateMessage(message: String) {
        translator.processMessage(message);
    }

    override fun addStockEventListener(listener: IStockEventListener) {
        translator.addStockEventListener(listener);
    }
}

