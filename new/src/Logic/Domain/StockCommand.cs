namespace AutoBuyer.Logic.Domain
{
    public struct StockCommand
    {
        private readonly String _content;

        public StockCommand(String content)
        {
            _content = content;
        }

        public override String ToString()
        {
            return _content;
        }

        public static StockCommand Buy(int price, int number)
        {
            return StockCommand($"Command: BUY; Price: {price}; Number: {number}");
        }

        public static StockCommand None()
        {
            return StockCommand(String.Empty);
        }

        public static bool operator ==(StockCommand left, StockCommand right)
        {
            return left.Equals(right);
        }

        public static bool operator !=(StockCommand left, StockCommand right)
        {
            return !(left == right);
        }
    }
}
