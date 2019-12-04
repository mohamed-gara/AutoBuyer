class MainViewModel : IBuyerListener, IPortfolioListener {
    private val listeners: List<IUserRequestListener>

    val buyers: ObservableCollection<BuyerViewModel>
    var startBuyingCommand:Command
        private set

    var newItemId: String
    var newItemMaximumPrice: Int
    var numberToBuy: Int

    constructor(portfolio: BuyerPortfolio) {
        startBuyingCommand = Command(Join);
        buyers = ObservableCollection<BuyerViewModel>();
        listeners = List<IUserRequestListener>();
        portfolio.addPortfolioListener(this);
    }

    private fun join() {
        for (listener in listeners) {
            listener.startBuying(newItemId, newItemMaximumPrice, numberToBuy);
        }
    }

    override fun buyerStateChanged(snapshot: BuyerSnapshot) {
        BuyerViewModel viewModel = Buyers . Single (x => x.ItemId == snapshot.ItemId);
        viewModel.UpdateState(snapshot);
    }

    override fun buyerAdded(buyer: Buyer) {
        var viewModel = BuyerViewModel(buyer.snapshot.itemId, buyer.snapshot);
        buyers.Add(viewModel);
        buyer.addBuyerListener(this);
    }

    fun addUserRequestListener(listenter: IUserRequestListener) {
        listeners.add(listenter);
    }
}
}
