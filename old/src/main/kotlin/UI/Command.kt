class Command : ICommand {
    private val execute: () -> Unit

    event EventHandler CanExecuteChanged

    {
        add { CommandManager.RequerySuggested += value; }
        remove { CommandManager.RequerySuggested -= value; }
    }


    constructor(execute: () -> Unit) {
        this.execute = execute;
    }

    fun execute(parameter: Object) {
        execute();
    }

    fun canExecute(parameter: Object): Boolean {
        return true;
    }
}

