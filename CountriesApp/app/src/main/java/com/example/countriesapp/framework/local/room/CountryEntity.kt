package com.example.countriesapp.framework.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "country_table")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "photo") var photoPath: String
)