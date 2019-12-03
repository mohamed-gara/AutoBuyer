import java.lang.IllegalArgumentException

class StockEvent {
    val type: StockEventType
    private val fields: Map<String, String>

    val currentprice: Int
        get() = getInt("CurrentPrice")
    val numberInStock: Int
        get() = getInt("NumberInStock")
    val numberSold: Int
        get() = getInt("NumberSold")
    val buyerName: String
        get() = get("BuyerName")

    private constructor(type: StockEventType, fields: Map<String, String>) {
        this.type = type;
        this.fields = fields;
    }

    private fun getInt(fieldName: String): Int {
        return Integer.parseInt(get(fieldName));
    }

    private fun get(fieldName: String): String {
        return fields[fieldName] ?: throw IllegalArgumentException(fieldName);
    }

    override fun toString(): String {
        return fields.toString()
    }

    companion object {
        fun from(message: String): StockEvent {
            if (!message.contains(":") || !message.contains(";"))
                throw IllegalArgumentException();

            val fields = getFields(message);
            val eventType = getEventType(fields);

            return StockEvent(eventType, fields);
        }

        private fun getEventType(fields: Map<String, String>): StockEventType {
            if (!fields.containsKey("Event"))
                throw IllegalArgumentException();

            return StockEventType.valueOf("Event")
        }

        private fun getFields(message: String): Map<String, String> {
            var fields = hashMapOf<String, String>()

            val pairs = message.split(";")
            for (pair in pairs) {
                val data = pair.split(":")
                fields.put(data[0].trim(), data[1].trim())
            }

            return fields
        }
    }
}


enum class StockEventType {
    Price,
    Purchase,
    Close
}

