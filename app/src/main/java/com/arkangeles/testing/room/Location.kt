package com.arkangeles.testing.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val lat: Double,
    val lon: Double,
    val cityName: String,
    val temp: Double
)