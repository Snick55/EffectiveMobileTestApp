package com.snick55.domain.repositories

interface TownsRepository {

    fun saveTown(town: String)
    fun getTown(): String

}