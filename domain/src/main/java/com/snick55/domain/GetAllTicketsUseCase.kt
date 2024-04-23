package com.snick55.domain

import com.snick55.domain.entities.AllTicketsItem
import com.snick55.domain.repositories.TicketsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTicketsUseCase @Inject constructor(
    private val repository: TicketsRepository
) {

    operator fun invoke(): Flow<List<AllTicketsItem>>{
        return repository.getAllTickets().map {
            it.map {item->
                item.copy(price = mapPrice(item.price))
            }
        }
    }

    private fun mapPrice(price: String): String{
        return when(price.length){
            4 -> "${price[0]} ${price.substring(1)}"
            5 -> "${price.substring(0,2)} ${price.substring(2)}"
            6 -> "${price.substring(0,3)} ${price.substring(3)}"
            else -> price
        }


    }

}