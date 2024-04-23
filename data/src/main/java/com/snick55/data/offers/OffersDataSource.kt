package com.snick55.data.offers

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

interface OffersDataSource {

    fun getOffers(): OffersResponse

    class OffersDataSourceImpl @Inject constructor(
        private val context: Context
    ): OffersDataSource {

        override fun getOffers(): OffersResponse {
           return context.getObjectFromJson<OffersResponse>("offers.json")
        }

       private fun inputStreamToString(inputStream: InputStream): String {
           return try {
               val bytes = ByteArray(inputStream.available())
               inputStream.read(bytes, 0, bytes.size)
               String(bytes)
           } catch (e: IOException) {
               ""
           }
        }

        private inline fun <reified T> Context.getObjectFromJson(jsonFileName: String): T {
            val myJson = inputStreamToString(this.assets.open(jsonFileName))
            return Gson().fromJson(myJson, T::class.java)
        }

    }

}