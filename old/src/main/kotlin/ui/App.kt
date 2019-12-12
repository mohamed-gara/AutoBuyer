/*class App {
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
}*/
package ui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class HelloFX : Application() {
    override fun start(stage: Stage) {
        val resource = javaClass.getResource("MainWindow.fxml")
        val root = FXMLLoader.load<Parent>(resource)
        val scene = Scene(root, 630.0, 350.0)
        stage.scene = scene
        stage.title = "MainWindow";
        stage.show()
    }
}

fun main() {
    Application.launch(HelloFX::class.java)
}
