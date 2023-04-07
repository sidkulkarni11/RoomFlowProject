package com.sid.hassanandroidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid.hassanandroidproject.adapter.StudentReportAdapter
import com.sid.hassanandroidproject.databinding.ActivityAddStudentBinding
import com.sid.hassanandroidproject.databinding.ActivityMainBinding
import com.sid.hassanandroidproject.model.IStudentReportOperation
import com.sid.hassanandroidproject.model.StudentReport

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var studentViewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        var iStudentReportOperation = object : IStudentReportOperation{
            override fun deleteStudent(studentReport: StudentReport) {
                studentViewModel.deleteStudent(studentReport)

            }

        }


        activityMainBinding.btnAddStudent.setOnClickListener {
            var addStudentActivityIntent = Intent(this,AddStudentActivity::class.java)
            startActivity(addStudentActivityIntent)
        }

        studentViewModel.allStudentReports.observe(this,{
            var studentReportAdapter= StudentReportAdapter(this,it)
            studentReportAdapter.setStudentOperation(iStudentReportOperation)
            activityMainBinding.studentReportRecyclerview.layoutManager =
                LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )

            activityMainBinding.studentReportRecyclerview.adapter = studentReportAdapter

            /*for(student in it){
                Log.d("***student",student.studentName)
            }*/
        })
    }


}