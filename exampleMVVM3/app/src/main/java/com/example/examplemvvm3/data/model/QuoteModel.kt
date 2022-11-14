package com.example.examplemvvm3.data.model

import com.example.examplemvvm3.domain.model.Quote
import com.google.gson.annotations.SerializedName

//La informacion de respuesta que recibamos de retrofit la vamos a convertir en este modelo de datos
//Por algun caso los parametros no se llaman igual no va a funcionar
//Si no son iguales toca colocar el serializedName
data class QuoteModel(
    @SerializedName("quote") var quote: String,
    @SerializedName("author") var author: String
)

fun Quote.toDataRepository()=QuoteModel(quote=quote, author=author)