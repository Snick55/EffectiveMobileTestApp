package com.snick55.presentation.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snick55.domain.GetAllOffersUseCase
import com.snick55.domain.TownsInteractor
import javax.inject.Inject

class AviaTicketsViewModelFactory @Inject constructor(
    private val useCase: GetAllOffersUseCase,
    private val interactor: TownsInteractor
  // @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == AviaTicketsViewModel::class.java)
        return AviaTicketsViewModel(useCase,interactor) as T
    }


}