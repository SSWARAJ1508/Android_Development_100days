package com.example.lab_8

import android.app.Application
import android.telephony.SmsManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AttendeeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AttendeeDatabase.getDatabase(application)
    private val attendeeDao = db.attendeeDao()

    val allAttendees: Flow<List<Attendee>> = attendeeDao.getAllAttendees()

    fun addAttendee(name: String, age: Int, phoneNumber: String) {
        viewModelScope.launch {
            val attendee = Attendee(name = name, age = age, phoneNumber = phoneNumber)
            attendeeDao.insertAttendee(attendee)
            sendSms(phoneNumber, "Welcome to the AI Conference, $name!")
        }
    }

    fun updateAttendee(attendee: Attendee) {
        viewModelScope.launch {
            attendeeDao.updateAttendee(attendee)
        }
    }

    fun deleteAttendee(attendee: Attendee) {
        viewModelScope.launch {
            attendeeDao.deleteAttendee(attendee)
        }
    }

    private fun sendSms(phoneNumber: String, message: String) {
        try {
            val smsManager: SmsManager = getApplication<Application>().getSystemService(SmsManager::class.java)
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
