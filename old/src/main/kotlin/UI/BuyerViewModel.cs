using AutoBuyer.Logic;

namespace AutoBuyer.UI
{
    public class BuyerViewModel : ViewModel
    {
        public String ItemId { get; }
        public String CurrentPrice { get; private set; }
        public String NumberInStock { get; private set; }
        public String BoughtSoFar { get; private set; }
        public String State { get; private set; }

        public BuyerViewModel(String itemId, BuyerSnapshot snapshot)
        {
            ItemId = itemId;
            UpdateState(snapshot);
        }

        public void UpdateState(BuyerSnapshot snapshot)
        {
            CurrentPrice = snapshot.CurrentPrice.ToString();
            NumberInStock = snapshot.NumberInStock.ToString();
            BoughtSoFar = snapshot.BoughtSoFar.ToString();
            State = snapshot.State.ToString();

            Notify(nameof(CurrentPrice));
            Notify(nameof(NumberInStock));
            Notify(nameof(BoughtSoFar));
            Notify(nameof(State));
        }
    }
}
