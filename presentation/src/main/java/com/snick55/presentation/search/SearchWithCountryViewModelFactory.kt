package com.snick55.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snick55.domain.repositories.TicketsRepository
import javax.inject.Inject

class SearchWithCountryViewModelFactory @Inject constructor(
    private val repository: TicketsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == SearchWithCountryViewModel::class.java)
        return SearchWithCountryViewModel(repository) as T
    }
}