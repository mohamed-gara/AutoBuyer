using AutoBuyer.Logic;

namespace AutoBuyer.UI
{
    public partial class App
    {
        private const String BuyerName = "Buyer";

        public App()
        {
            var connection = WarehouseConnection();
            var warehouse = Warehouse(connection, BuyerName);

            var portfolio = BuyerPortfolio();
            var launcher = BuyerLauncher(warehouse, portfolio);
            var mainViewModel = MainViewModel(portfolio);
            mainViewModel.AddUserRequestListener(launcher);

            var window = MainWindow
            {
                DataContext = mainViewModel
            };
            window.ShowDialog();
        }
    }
}
