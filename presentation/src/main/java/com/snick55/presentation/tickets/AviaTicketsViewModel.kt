package com.snick55.presentation.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snick55.domain.entities.AviaOffer
import com.snick55.domain.GetAllOffersUseCase
import com.snick55.domain.TownsInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AviaTicketsViewModel @Inject constructor(
    private val getAllOffersUseCase: GetAllOffersUseCase,
    private val interactor: TownsInteractor
): ViewModel() {


    private val innerOffers = MutableLiveData<List<AviaOffer>>()
    val offers: LiveData<List<AviaOffer>> = innerOffers

    init {
      viewModelScope.launch() {
          getAllOffersUseCase.invoke().collect{
              innerOffers.postValue(it)
          }
      }
    }

    fun getTown() = interactor.getTown()

    fun saveTown(fromTown: String) {
        interactor.saveTown(fromTown)
    }

}