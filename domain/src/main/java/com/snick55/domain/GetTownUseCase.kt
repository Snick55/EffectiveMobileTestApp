package com.snick55.domain

import com.snick55.domain.repositories.TownsRepository
import javax.inject.Inject

 class GetTownUseCase @Inject constructor(
    private val repository: TownsRepository
) {
    operator fun invoke(): String{
        return repository.getTown()
    }
}