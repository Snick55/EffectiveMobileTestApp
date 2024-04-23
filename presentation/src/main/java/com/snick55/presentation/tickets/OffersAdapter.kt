package com.snick55.presentation.tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snick55.domain.entities.AviaOffer
import com.snick55.presentation.R
import com.snick55.presentation.databinding.OfferItemBinding

class OffersAdapter : ListAdapter<AviaOffer, OffersAdapter.MyViewHolder>(OffersDiffCallback()) {

    inner class MyViewHolder(private val binding: OfferItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(itemUi: AviaOffer){
            binding.offerTitle.text = itemUi.title
            binding.offerTown.text = itemUi.town
            binding.offerPrice.text = binding.root.context.getString(R.string.offer_price_title,itemUi.price)
            Glide.with(binding.offerImage.context).load(itemUi.image).into(binding.offerImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OfferItemBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }





    class OffersDiffCallback : DiffUtil.ItemCallback<AviaOffer>() {
        override fun areItemsTheSame(oldItem: AviaOffer, newItem: AviaOffer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AviaOffer, newItem: AviaOffer): Boolean {
            return oldItem == newItem
        }
    }
}