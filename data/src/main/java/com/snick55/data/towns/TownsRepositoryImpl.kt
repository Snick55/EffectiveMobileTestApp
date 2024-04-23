package com.snick55.data.towns

import com.snick55.data.towns.TownsPreferenceDataSource
import com.snick55.domain.repositories.TownsRepository
import javax.inject.Inject

class TownsRepositoryImpl @Inject constructor(
    private val preferenceDataSource: TownsPreferenceDataSource
): TownsRepository {

    override fun saveTown(town: String) {
        preferenceDataSource.saveTown(town)
    }

    override fun getTown(): String {
        return  preferenceDataSource.getTown()
    }
}