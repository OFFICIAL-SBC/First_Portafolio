package com.example.examplemvvm3.domain.model

import com.example.examplemvvm3.data.database.entities.QuoteEntity
import com.example.examplemvvm3.data.model.QuoteModel

/*Mapper es una funcion que me permite transformar un objeto en otro
* esta funcion debe ir en el objeto final o resultante
*/
data class Quote(val quote: String, val author:String)

fun QuoteModel.toDomain()=Quote(quote,author)
fun QuoteEntity.toDomain()=Quote(quote,author)