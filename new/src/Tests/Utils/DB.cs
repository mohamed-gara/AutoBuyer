using System.Data.SqlClient;
using System.Linq;
using AutoBuyer.Logic.Domain;
using Dapper;

namespace Tests.Utils
{
    public static class DB
    {
        public static BuyerDto GetBuyer(String itemId)
        {
            using (SqlConnection connection = SqlConnection(Integration.Tests.ConnectionString))
            {
                String query = "SELECT * FROM dbo.Buyer WHERE ItemID = @ItemID";
                return connection.Query<BuyerDto>(query, { ItemID = itemId }).Single();
            }
        }
    }

    public struct BuyerDto
    {
        public readonly String BuyerName;
        public readonly int MaximumPrice;
        public readonly int NumberToBuy;
        public readonly int CurrentPrice;
        public readonly int NumberInStock;
        public readonly int BoughtSoFar;
        public readonly BuyerState State;
    }
}
