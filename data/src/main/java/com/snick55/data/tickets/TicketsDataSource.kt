package com.snick55.data.tickets

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

interface TicketsDataSource {

    fun getAllOffersTickets(): TicketsResponse

    fun getAllTickets(): AllTicketsResponse


    class TicketsDataSourceImpl @Inject constructor(
        private val context: Context
    ): TicketsDataSource {

        override fun getAllOffersTickets(): TicketsResponse {
            return context.getObjectFromJson<TicketsResponse>("tickets_offers.json")
        }

        override fun getAllTickets(): AllTicketsResponse {
            return context.getObjectFromJson("tickets.json")
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