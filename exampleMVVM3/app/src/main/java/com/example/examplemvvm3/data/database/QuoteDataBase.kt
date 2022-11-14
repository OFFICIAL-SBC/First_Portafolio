package com.example.examplemvvm3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examplemvvm3.data.database.dao.QuoteDao
import com.example.examplemvvm3.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class],version = 1)
abstract class QuoteDataBase:RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
}