using System;

namespace AutoBuyer.Logic.Connections
{
    public interface IStockItemConnection
    {
        event Action<String> MessageReceived;
        void SendMessage(String message);
    }
}
