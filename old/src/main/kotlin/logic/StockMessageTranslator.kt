import StockEventType.*

class StockMessageTranslator {
    private val listeners = arrayListOf<IStockEventListener>()
    private val buyerName: String

    constructor(buyerName: String) {
        this.buyerName = buyerName;
    }

    fun addStockEventListener(listener: IStockEventListener) {
        listeners.add(listener);
    }

    fun processMessage(message: String) {
        val stockEvent = StockEvent.from(message);
        when(stockEvent.type)
        {
            Price -> notify {it.currentPrice(stockEvent.currentprice, stockEvent.numberInStock)}
            Purchase -> notify {it.itemPurchased(stockEvent.numberSold, getEventSource(stockEvent.buyerName))}
            Close -> notify {it.itemClosed()}
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getEventSource(buyerName:String): PurchaseSource {
        return if(this.buyerName == buyerName) PurchaseSource.FromBuyer else PurchaseSource.FromOtherBuyer
    }

    private fun notify(action: (IStockEventListener) -> Unit) {
        for (listener in listeners) {
            action(listener);
        }
    }
}

