package com.snick55.domain.entities

data class AllTicketsItem(
    val id: Int,
    val badge: String? = null,
    val price: String,
    val departureDate: String,
    val departureAirport: String,
    val arrivalDate: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean
)
