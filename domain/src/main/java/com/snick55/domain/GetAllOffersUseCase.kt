package com.snick55.domain

import com.snick55.domain.entities.AviaOffer
import com.snick55.domain.repositories.OffersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllOffersUseCase @Inject constructor(
   private val repository: OffersRepository
) {
     operator fun invoke(): Flow<List<AviaOffer>> {
        return repository.getAllOffers()
    }
}