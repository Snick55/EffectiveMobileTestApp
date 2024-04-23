package com.snick55.data.tickets

import com.snick55.domain.entities.AllTicketsItem
import com.snick55.domain.entities.OfferTicket
import com.snick55.domain.repositories.TicketsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketsDataSource: TicketsDataSource
): TicketsRepository {

    override fun getAllOffersTickets(): Flow<List<OfferTicket>> = flow {
        emit(ticketsDataSource.getAllOffersTickets().tickets_offers.map {
            it.toOfferTickets()
        })
    }

    override fun getAllTickets(): Flow<List<AllTicketsItem>> = flow{
        emit(ticketsDataSource.getAllTickets().tickets.map {
            it.toAllTicketsItem()
        })
    }
}

fun TicketsResponse.TicketsOffer.toOfferTickets() = OfferTicket(
    id,title,time_range,price.value
)

fun AllTicketsResponse.Ticket.toAllTicketsItem() = AllTicketsItem(
    id,badge,price.value.toString(),departure.date,departure.airport,arrival.date,arrival.airport,has_transfer
)