package com.sid.hassanandroidproject

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid.hassanandroidproject.adapter.SubjectAdapter
import com.sid.hassanandroidproject.databinding.ActivityAddStudentBinding
import com.sid.hassanandroidproject.model.Course
import com.sid.hassanandroidproject.model.StudentReport
import com.sid.hassanandroidproject.model.Subject

class AddStudentActivity : AppCompatActivity() {
    lateinit var addStudentBinding: ActivityAddStudentBinding
    lateinit var subjectList: ArrayList<Subject>
    lateinit var subjectAdapter: SubjectAdapter

    lateinit var studentViewModel: StudentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addStudentBinding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(addStudentBinding.root)

        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        var data = ArrayList<String>()
        data.add("")
        data.add("CSE")
        data.add("ECE")
        data.add("Mech")
        data.add("Civil")

        addStudentBinding.spinnerCourse.adapter =
            ArrayAdapter<String>(this, R.layout.simple_list_item_1, data)


        subjectList = ArrayList()

        addStudentBinding.spinnerCourse.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    subjectList.clear()

                    subjectList = getListByPosition(position) as ArrayList<Subject>

                    subjectAdapter = SubjectAdapter( subjectList)

                    addStudentBinding.subjectRecyclerView.visibility = View.VISIBLE

                    addStudentBinding.subjectRecyclerView.layoutManager =
                        LinearLayoutManager(
                            applicationContext,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    addStudentBinding.subjectRecyclerView.adapter = subjectAdapter
                }

            }




        addStudentBinding.saveButton.setOnClickListener {
            var rollNo = addStudentBinding.edtvStudentRollNo.text.toString().toInt()
            var studentName = addStudentBinding.edtvStudentName.text.toString()

            var courseName = addStudentBinding.spinnerCourse.selectedItem.toString()
            var course = Course(courseName, subjectList)

            var total = subjectAdapter.getTotal()
            var average = subjectAdapter.getAvg()

            var studentReport = StudentReport(
                rollNo,
                studentName,
                course,
                total,
                average
            )

            studentViewModel.insertStudent(studentReport)
            finish()
            var mainActivityIntent = Intent(this,MainActivity::class.java)
            startActivity(mainActivityIntent)
        }
    }


    fun getECESubjectList(): List<Subject> {
        var ecelist = ArrayList<Subject>()
        ecelist.add(Subject("Digital electronics"))
        ecelist.add(Subject("Maths"))
        ecelist.add(Subject("Microprocessor"))
        return ecelist
    }

    fun getCSESubjectList(): List<Subject> {
        var cselist = ArrayList<Subject>()
        cselist.add(Subject("C language"))
        cselist.add(Subject("Data Structure"))
        cselist.add(Subject("Java"))
        return cselist
    }

    fun getMechSubjectList(): List<Subject> {
        var mechList = ArrayList<Subject>()
        mechList.add(Subject("NanoTechnology"))
        mechList.add(Subject("Biometrics"))
        mechList.add(Subject("Acoustics"))
        return mechList
    }

    fun getCivilList(): List<Subject> {
        var civilList = ArrayList<Subject>()
        civilList.add(Subject("Material Science"))
        civilList.add(Subject("Construction Engineering"))
        civilList.add(Subject("Hydraulic science"))
        return civilList
    }

    fun getListByPosition(position: Int): List<Subject> {
        when (position) {
            1 -> return getCSESubjectList()
            2 -> return getECESubjectList()
            3 -> return getMechSubjectList()
            4 -> return getCivilList()
        }
        return ArrayList<Subject>()
    }
}