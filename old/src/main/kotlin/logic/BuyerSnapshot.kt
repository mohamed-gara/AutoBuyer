import BuyerState.*

data class BuyerSnapshot(
        val itemId: String,
        val currentPrice: Int,
        val numberInStock: Int,
        val boughtSoFar: Int,
        val state: BuyerState) {
    fun monitoring(currentPrice: Int, numberInStock: Int): BuyerSnapshot {
        return BuyerSnapshot(itemId, currentPrice, numberInStock, boughtSoFar, Monitoring);
    }

    fun bought(numberBought: Int): BuyerSnapshot {
        return BuyerSnapshot(itemId, currentPrice, numberInStock - numberBought, boughtSoFar + numberBought, state);
    }

    fun closed(): BuyerSnapshot {
        return BuyerSnapshot(itemId, currentPrice, numberInStock, boughtSoFar, Closed);
    }

    fun buying(currentPrice: Int, numberInStock: Int): BuyerSnapshot {
        return BuyerSnapshot(itemId, currentPrice, numberInStock, boughtSoFar, Buying);
    }
}

fun joining(itemId: String): BuyerSnapshot {
    return BuyerSnapshot(itemId, 0, 0, 0, Joining)
}
