abstract class ViewModel : INotifyPropertyChanged
    {
        //public event PropertyChangedEventHandler PropertyChanged;

        protected fun notify( propertyName:String) {
            //PropertyChanged?.Invoke(this, PropertyChangedEventArgs(propertyName));
        }
    }

