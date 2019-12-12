/*
namespace AutoBuyer.UI
{
    public partial class MainWindow
    {
        public MainWindow()
        {
            InitializeComponent();
        }
    }
}
*/

package ui

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.text.Text

class HelloController {
    @FXML
    private val actiontarget: Text? = null

    @FXML
    protected fun startBuyingCommand(event: ActionEvent?) {
        actiontarget!!.text = "Sign in button pressed"
    }
}
