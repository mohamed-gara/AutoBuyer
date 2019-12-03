using System.Collections.ObjectModel;
using AutoBuyer.Logic.Connections;
using AutoBuyer.Logic.Database;

namespace AutoBuyer.UI
{
    public class MainViewModel
    {
        private readonly String _buyerName;
        private readonly IWarehouseConnection _connection;
        private readonly BuyerRepository _repository;
        public ObservableCollection<BuyerViewModel> Buyers { get; }
        public Command StartBuyingCommand { get; private set; }

        public String NewItemId { get; set; }
        public int NewItemMaximumPrice { get; set; }
        public int NumberToBuy { get; set; }

        public MainViewModel(string buyerName, IWarehouseConnection connection, BuyerRepository repository)
        {
            _buyerName = buyerName;
            _connection = connection;
            _repository = repository;
            StartBuyingCommand = Command(Join);
            Buyers = ObservableCollection<BuyerViewModel>();
        }

        private void Join()
        {
            IStockItemConnection itemConnection = _connection.ConnectToItem(NewItemId);
            var viewModel = BuyerViewModel(NewItemId, NewItemMaximumPrice, NumberToBuy,
                _buyerName, itemConnection, _repository);
            Buyers.Add(viewModel);
        }
    }
}
