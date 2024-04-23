package com.snick55.data.towns

import android.content.SharedPreferences
import javax.inject.Inject

interface TownsPreferenceDataSource {

    fun saveTown(town: String)
    fun getTown(): String

    class TownsPreferenceDataSourceImpl @Inject constructor (private val sharedPreferences: SharedPreferences):
        TownsPreferenceDataSource {

        override fun saveTown(town: String) {
            sharedPreferences.edit().putString(KEY_TOWN,town).apply()
        }

        override fun getTown(): String {
            return sharedPreferences.getString(KEY_TOWN,"")?: ""
        }

        private companion object{

            const val KEY_TOWN = "KEY_TOWN"
        }

    }

}