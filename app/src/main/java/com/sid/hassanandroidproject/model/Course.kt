package com.sid.hassanandroidproject.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.TypeConverters

data class Course(
    var courseName : String,

    @TypeConverters(Converters::class)
    var subjects : List<Subject>
)