package com.example.lab_8

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Attendee::class], version = 1, exportSchema = false)
abstract class AttendeeDatabase : RoomDatabase() {
    abstract fun attendeeDao(): AttendeeDao

    companion object {
        @Volatile
        private var INSTANCE: AttendeeDatabase? = null

        fun getDatabase(context: Context): AttendeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendeeDatabase::class.java,
                    "attendee_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
