package com.snick55.presentation.allTickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snick55.domain.GetAllTicketsUseCase
import javax.inject.Inject

class AllTicketsViewModelFactory@Inject constructor(
    private val getAllTicketsUseCase: GetAllTicketsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == AllTicketsViewModel::class.java)
        return AllTicketsViewModel(getAllTicketsUseCase) as T
    }
}