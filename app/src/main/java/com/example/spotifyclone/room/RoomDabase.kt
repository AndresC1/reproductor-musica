package com.example.spotifyclone.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [SongModel::class], version = 3)
abstract class RoomDabase: RoomDatabase() {
    abstract fun SongDao(): SongDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDabase? = null

        fun getDatabase(context: Context): RoomDabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDabase::class.java,
                    "canciones"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}