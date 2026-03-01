package com.example.dataroom

import android.app.Application

class StudentApp : Application() {
    val database: StudentDatabase by lazy { StudentDatabase.getDatabase(this) }
}
