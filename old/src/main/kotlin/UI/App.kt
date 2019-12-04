class App {
    val buyerName = "Buyer";

    constructor() {
        val connection = WarehouseConnection();
        val warehouse = Warehouse(connection, buyerName);

        val portfolio = BuyerPortfolio();
        val launcher = BuyerLauncher(warehouse, portfolio);
        val mainViewModel = MainViewModel(portfolio);
        mainViewModel.addUserRequestListener(launcher);

        val window = MainWindow
        {
            DataContext = mainViewModel
        };
        window.ShowDialog();
    }
}

