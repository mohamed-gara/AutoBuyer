namespace AutoBuyer.Logic.Connections
{
    public interface IWarehouseConnection
    {
        IStockItemConnection ConnectToItem(String itemId);
    }
}
