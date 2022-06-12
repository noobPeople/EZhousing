package com.okky.ezhousing.database

import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tanah::class], version = 1, exportSchema = false)
abstract class TanahDatabase : RoomDatabase() {
    abstract fun tanahDao(): TanahDao

    companion object {
        @Volatile
        private var instance: TanahDatabase? = null
        fun getInstance(context: FragmentActivity): TanahDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TanahDatabase::class.java, "Tanah.db"
                ).build()
            }
    }
}