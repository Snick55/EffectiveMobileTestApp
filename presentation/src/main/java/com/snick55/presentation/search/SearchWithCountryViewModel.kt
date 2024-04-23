package com.snick55.presentation.search

import androidx.lifecycle.ViewModel
import com.snick55.domain.repositories.TicketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchWithCountryViewModel @Inject constructor(
    private val repository: TicketsRepository
): ViewModel() {

    val tickets = repository.getAllOffersTickets().flowOn(Dispatchers.IO)

}