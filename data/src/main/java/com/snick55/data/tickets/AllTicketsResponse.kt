package com.snick55.data.tickets

data class AllTicketsResponse(
    val tickets: List<Ticket>
) {
    data class Ticket(
        val arrival: Arrival,
        val badge: String,
        val company: String,
        val departure: Departure,
        val hand_luggage: HandLuggage,
        val has_transfer: Boolean,
        val has_visa_transfer: Boolean,
        val id: Int,
        val is_exchangable: Boolean,
        val is_returnable: Boolean,
        val luggage: Luggage,
        val price: Price,
        val provider_name: String
    ) {
        data class Arrival(
            val airport: String,
            val date: String,
            val town: String
        )

        data class Departure(
            val airport: String,
            val date: String,
            val town: String
        )

        data class HandLuggage(
            val has_hand_luggage: Boolean,
            val size: String
        )

        data class Luggage(
            val has_luggage: Boolean,
            val price: Price
        ) {
            data class Price(
                val value: Int
            )
        }

        data class Price(
            val value: Int
        )
    }
}