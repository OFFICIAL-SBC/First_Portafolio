package com.example.countriesapp.framework.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class], version = 2)
abstract class CountryDatabase: RoomDatabase() {

    companion object{
        private const val NAME_DATABASE ="country.db"

        private var instance: CountryDatabase? = null

        private fun create(context: Context): CountryDatabase =
            Room.databaseBuilder(context,CountryDatabase::class.java, NAME_DATABASE)
                .fallbackToDestructiveMigration() //This was done when I added a new column in the database
                .build()

        fun getInstance(context: Context) =(instance ?: create(context)).also { instance = it }
    }

    abstract fun getCountryDao():CountryDao

}