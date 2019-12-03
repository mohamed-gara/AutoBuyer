using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using AutoBuyer.Logic;

namespace AutoBuyer.UI
{
    public class MainViewModel : IBuyerListener, IPortfolioListener
    {
        private readonly List<IUserRequestListener> _listeners;

        public ObservableCollection<BuyerViewModel> Buyers { get; }
        public Command StartBuyingCommand { get; private set; }

        public String NewItemId { get; set; }
        public int NewItemMaximumPrice { get; set; }
        public int NumberToBuy { get; set; }

        public MainViewModel(BuyerPortfolio portfolio)
        {
            StartBuyingCommand = Command(Join);
            Buyers = ObservableCollection<BuyerViewModel>();
            _listeners = List<IUserRequestListener>();
            portfolio.AddPortfolioListener(this);
        }

        private void Join()
        {
            foreach (IUserRequestListener listener in _listeners)
            {
                listener.StartBuying(NewItemId, NewItemMaximumPrice, NumberToBuy);
            }
        }

        public void BuyerStateChanged(BuyerSnapshot snapshot)
        {
            BuyerViewModel viewModel = Buyers.Single(x => x.ItemId == snapshot.ItemId);
            viewModel.UpdateState(snapshot);
        }

        public void BuyerAdded(Buyer buyer)
        {
            var viewModel = BuyerViewModel(buyer.Snapshot.ItemId, buyer.Snapshot);
            Buyers.Add(viewModel);
            buyer.AddBuyerListener(this);
        }

        public void AddUserRequestListener(IUserRequestListener listenter)
        {
            _listeners.Add(listenter);
        }
    }
}
