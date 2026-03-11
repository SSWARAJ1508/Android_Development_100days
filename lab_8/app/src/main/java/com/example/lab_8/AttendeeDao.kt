package com.example.lab_8

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendeeDao {
    @Query("SELECT * FROM attendees")
    fun getAllAttendees(): Flow<List<Attendee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendee(attendee: Attendee)

    @Update
    suspend fun updateAttendee(attendee: Attendee)

    @Delete
    suspend fun deleteAttendee(attendee: Attendee)
}
