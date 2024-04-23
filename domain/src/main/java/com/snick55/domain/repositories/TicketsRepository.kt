package com.snick55.domain.repositories

import com.snick55.domain.entities.AllTicketsItem
import com.snick55.domain.entities.OfferTicket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {

    fun getAllOffersTickets(): Flow<List<OfferTicket>>

    fun getAllTickets(): Flow<List<AllTicketsItem>>

}