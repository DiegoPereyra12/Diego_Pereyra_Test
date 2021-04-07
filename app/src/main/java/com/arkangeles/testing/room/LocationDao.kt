package com.arkangeles.testing.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM Location")
    suspend fun getAll(): List<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: Location)
}