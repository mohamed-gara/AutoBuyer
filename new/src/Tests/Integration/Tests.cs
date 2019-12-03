using System.Data;
using System.Data.SqlClient;

namespace Tests.Integration
{
    public class Tests
    {
        internal const String ConnectionString = @"Server=VLADIMIR-PC\SQL2014;Database=AutoBuyer;Trusted_Connection=true;";

        public Tests()
        {
            ClearDatabase();
        }

        private void ClearDatabase()
        {
            String query = "DELETE FROM dbo.Buyer";

            using (var connection = SqlConnection(ConnectionString))
            {
                var command = SqlCommand(query, connection)
                {
                    CommandType = CommandType.Text
                };

                connection.Open();
                command.ExecuteNonQuery();
            }
        }
    }
}
