package com.sid.hassanandroidproject.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentreport")
data class StudentReport(
    @PrimaryKey
    var rollNo : Int,
    var studentName : String,
    @Embedded
    var course: Course,
    var total : Int = 0,
    var average : Double = 0.0
)
