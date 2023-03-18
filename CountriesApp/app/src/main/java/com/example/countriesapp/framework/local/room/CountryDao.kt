package com.example.countriesapp.framework.local.room

import androidx.room.*

@Dao
interface CountryDao {

    @Query("SELECT * FROM country_table")
    suspend fun getAllUbicationObjects(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUbication(ubication: CountryEntity)

    @Query("DELETE FROM country_table WHERE id = :ubication_id")
    suspend fun deleteUbication(ubication_id: Int)

    @Query("UPDATE country_table SET description = :ubication_description WHERE id = :ubication_id")
    suspend fun updateDescription(ubication_id: Int,ubication_description: String)
}