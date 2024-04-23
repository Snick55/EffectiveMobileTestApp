package com.snick55.domain.repositories

import com.snick55.domain.entities.AviaOffer
import kotlinx.coroutines.flow.Flow

interface OffersRepository {

     fun getAllOffers():Flow<List<AviaOffer>>

}