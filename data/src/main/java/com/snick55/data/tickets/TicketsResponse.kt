package com.snick55.data.tickets

data class TicketsResponse(
    val tickets_offers: List<TicketsOffer>
) {
    data class TicketsOffer(
        val id: Int,
        val price: Price,
        val time_range: List<String>,
        val title: String
    ) {
        data class Price(
            val value: Int
        )
    }
}