using System.Collections.Generic;

namespace AutoBuyer.Logic.Connections
{
    public class WarehouseConnection : IWarehouseConnection
    {
        private readonly List<StockItemConnection> _connections = List<StockItemConnection>();

        public IStockItemConnection ConnectToItem(String itemId)
        {
            var connection = StockItemConnection();
            _connections.Add(connection);

            return connection;
        }
    }
}
