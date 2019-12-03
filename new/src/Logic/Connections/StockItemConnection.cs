using System;

namespace AutoBuyer.Logic.Connections
{
    public class StockItemConnection : IStockItemConnection
    {
        public event Action<String> MessageReceived;

        public void SendMessage(String message)
        {
            // Send the message through a 3rd party SDK
        }
    }
}
