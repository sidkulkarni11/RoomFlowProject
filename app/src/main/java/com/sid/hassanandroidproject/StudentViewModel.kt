package com.sid.hassanandroidproject

import android.app.Application
import androidx.lifecycle.*
import com.sid.hassanandroidproject.database.AppDatabase
import com.sid.hassanandroidproject.model.StudentReport
import com.sid.hassanandroidproject.repo.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) :AndroidViewModel(application)  {
   var studentRepo : StudentRepository


   init {
       val studentDao = AppDatabase.getInstance(application).studentReportDao()
       studentRepo = StudentRepository(studentDao)
   }

    val allStudentReports : LiveData<List<StudentReport>> = studentRepo.allStudents.asLiveData()

    fun insertStudent(studentReport: StudentReport){
        viewModelScope.launch {
            studentRepo.insertStudent(studentReport)
        }
    }

    fun updateStudent(studentReport: StudentReport){
        viewModelScope.launch {
            studentRepo.updateStudent(studentReport)
        }
    }

    fun deleteStudent(studentReport: StudentReport){
        viewModelScope.launch {
            studentRepo.deleteStudent(studentReport)
        }
    }
}