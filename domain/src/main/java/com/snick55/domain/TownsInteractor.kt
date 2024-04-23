package com.snick55.domain

import javax.inject.Inject

interface TownsInteractor {

    fun saveTown(town: String)
    fun getTown(): String

    class TownsInteractorImpl @Inject constructor(
        private val saveTownUseCase: SaveTownUseCase,
        private val getTownUseCase: GetTownUseCase
    ): TownsInteractor{

        override fun saveTown(town: String) {
            saveTownUseCase.invoke(town)
        }

        override fun getTown(): String {
           return getTownUseCase()
        }
    }

}