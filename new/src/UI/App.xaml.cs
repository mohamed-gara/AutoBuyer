using AutoBuyer.Logic.Connections;
using AutoBuyer.Logic.Database;

namespace AutoBuyer.UI
{
    public partial class App
    {
        private const String BuyerName = "Buyer";
        private const String ConnectionString = @"Server=VLADIMIR-PC\SQL2014;Database=AutoBuyer;Trusted_Connection=true;";

        public App()
        {
            var connection = WarehouseConnection();
            var mainViewModel = MainViewModel(BuyerName, connection, BuyerRepository(ConnectionString));

            var window = MainWindow
            {
                DataContext = mainViewModel
            };
            window.ShowDialog();
        }
    }
}
