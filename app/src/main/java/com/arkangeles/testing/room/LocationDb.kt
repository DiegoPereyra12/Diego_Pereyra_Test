package com.arkangeles.testing.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Location::class], version = 1)
abstract class LocationDb : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    companion object{
        private var INSTANCE : LocationDb?= null

        fun getInstance(context: Context): LocationDb{
            if (INSTANCE == null){
               INSTANCE = Room.databaseBuilder(context.applicationContext, LocationDb::class.java,"locationdb").build()
            }
            return INSTANCE!!
        }
    }
}