package com.sid.hassanandroidproject.model

import androidx.room.Entity

data class Subject(
    var subjectName : String,
    var subjectMarks : Int = 0
){
    constructor() : this("")
}
