package com.snick55.presentation.allTickets

import androidx.lifecycle.ViewModel
import com.snick55.domain.GetAllTicketsUseCase
import javax.inject.Inject

class AllTicketsViewModel @Inject constructor(
    getAllTicketsUseCase: GetAllTicketsUseCase
): ViewModel() {

  val tickets = getAllTicketsUseCase.invoke()

}