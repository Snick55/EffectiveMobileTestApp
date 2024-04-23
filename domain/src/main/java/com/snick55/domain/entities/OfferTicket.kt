package com.snick55.domain.entities

data class OfferTicket(
    val id: Int,
    val title: String,
    val times: List<String>,
    val price: Int
)
