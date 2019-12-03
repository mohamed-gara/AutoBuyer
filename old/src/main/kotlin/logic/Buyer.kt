class Buyer : IStockEventListener {

    private val listeners = arrayListOf<IBuyerListener>();
    private val maximumPrice: Int
    private val numberToBuy: Int
    private val aStockitem: IStockItem
    var snapshot: BuyerSnapshot
        private set

    constructor(itemId: String, maximumPrice: Int, numberToBuy: Int, aStockItem: IStockItem) {
        this.numberToBuy = numberToBuy
        this.maximumPrice = maximumPrice
        aStockitem = aStockItem
        snapshot = joining(itemId)
    }

    fun addBuyerListener(aListener: IBuyerListener) {
        listeners.add(aListener);
    }

    override fun currentPrice(price: Int, numberInStock: Int) {
        if (snapshot.state == BuyerState.Closed)
            return;

        if (price > maximumPrice) {
            snapshot = snapshot.monitoring(price, numberInStock);
        } else {
            val number = Math.min(numberInStock, numberToBuy)
            aStockitem.buy(price, number)
            snapshot = snapshot.buying(price, numberInStock)
        }
        notifyChange();
    }

    override fun itemPurchased(numberSold: Int, purchaseSource: PurchaseSource) {
        if (purchaseSource == PurchaseSource.FromBuyer) {
            snapshot = snapshot.bought(numberSold);
            if (snapshot.boughtSoFar >= numberToBuy) {
                snapshot = snapshot.closed();
            }
            notifyChange();
        }
    }

    override fun itemClosed() {
        snapshot = snapshot.closed();
        notifyChange();
    }

    private fun notifyChange() {
        for (listener in listeners) {
            listener.buyerStateChanged(snapshot);
        }
    }
}

