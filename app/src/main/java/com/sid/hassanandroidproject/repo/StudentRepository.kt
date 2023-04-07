package com.sid.hassanandroidproject.repo

import com.sid.hassanandroidproject.dao.StudentDao
import com.sid.hassanandroidproject.model.StudentReport
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao){



    val allStudents : Flow<List<StudentReport>> = studentDao.getAllStudents()

    suspend fun insertStudent(studentReport: StudentReport) {
        studentDao.insert(studentReport)
    }

    suspend fun updateStudent(studentReport: StudentReport) {
        studentDao.update(studentReport)
    }

    suspend fun deleteStudent(studentReport: StudentReport) {
        studentDao.delete(studentReport)
    }
}