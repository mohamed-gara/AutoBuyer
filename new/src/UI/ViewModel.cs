using System.ComponentModel;

namespace AutoBuyer.UI
{
    public abstract class ViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        protected void Notify(String propertyName)
        {
            PropertyChanged?.Invoke(this, PropertyChangedEventArgs(propertyName));
        }
    }
}
