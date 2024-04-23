package com.snick55.presentation.allTickets

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.snick55.domain.entities.AllTicketsItem
import com.snick55.presentation.databinding.TicketItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TicketsAdapter :
    ListAdapter<AllTicketsItem, TicketsAdapter.MyViewHolder>(OffersDiffCallback()) {

    inner class MyViewHolder(private val binding: TicketItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(itemUi: AllTicketsItem) {
            val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val firstDateTime = LocalDateTime.parse(itemUi.departureDate, dateTimeFormatter)
            val secondDateTime = LocalDateTime.parse(itemUi.arrivalDate, dateTimeFormatter)

            val duration = java.time.Duration.between(firstDateTime, secondDateTime)
            val time = (duration.toMinutes() / 60.0).toString()
            binding.airportFrom.text = itemUi.departureAirport
            binding.airportTo.text = itemUi.arrivalAirport
            binding.priceTV.text = "${itemUi.price}₽"
            binding.hotDescription.visibility =
                if (itemUi.badge == null) View.INVISIBLE else View.VISIBLE
            binding.hotTitle.text = itemUi.badge ?: ""
            binding.timeTV.text =
                "${itemUi.departureDate.substring(11, 16)}-${itemUi.arrivalDate.substring(11, 16)}"
            binding.description.text = if (itemUi.hasTransfer) "${
                time.substring(
                    0,
                    3
                )
            }часа в пути/без пересадок" else "${time.substring(0, 3)}часа в пути"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TicketItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class OffersDiffCallback : DiffUtil.ItemCallback<AllTicketsItem>() {
        override fun areItemsTheSame(oldItem: AllTicketsItem, newItem: AllTicketsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AllTicketsItem, newItem: AllTicketsItem): Boolean {
            return oldItem == newItem
        }
    }
}