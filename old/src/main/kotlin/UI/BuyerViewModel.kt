class BuyerViewModel : ViewModel {
    val itemId:String
    var currentPrice:String = ""
        private set
    var numberInStock:String = ""
        private set
    var boughtSoFar:String = ""
        private set
    var state:String = ""
        private set

    constructor(itemId: String, snapshot: BuyerSnapshot) {
        this.itemId = itemId
        updateState(snapshot)
    }

    fun updateState( snapshot:BuyerSnapshot)
    {
        currentPrice = snapshot.currentPrice.toString()
        numberInStock = snapshot.numberInStock.toString()
        boughtSoFar = snapshot.boughtSoFar.toString()
        state = snapshot.state.toString()

        notify(nameof(currentPrice))
        notify(nameof(numberInStock))
        notify(nameof(boughtSoFar))
        notify(nameof(state))
    }
}
