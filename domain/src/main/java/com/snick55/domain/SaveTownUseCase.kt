package com.snick55.domain

import com.snick55.domain.repositories.TownsRepository
import javax.inject.Inject

class SaveTownUseCase@Inject constructor(
    private val repository: TownsRepository
) {
    operator fun invoke(town: String){
        if (town.isBlank()) return
         repository.saveTown(town)
    }
}