package com.sid.hassanandroidproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sid.hassanandroidproject.databinding.StudentReportLayoutBinding
import com.sid.hassanandroidproject.model.IStudentReportOperation
import com.sid.hassanandroidproject.model.StudentReport

class StudentReportAdapter(context: Context,studentReports : List<StudentReport>) : RecyclerView.Adapter<StudentReportAdapter.StudentReportVH>() {
    var studentReportList : List<StudentReport>
    var mContext : Context ?= null
    var iStudentReportOperation : IStudentReportOperation ?= null
    init {
        studentReportList = studentReports
        mContext = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentReportVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val studentReportLayoutBinding = StudentReportLayoutBinding.inflate(layoutInflater)
        return  StudentReportVH(studentReportLayoutBinding)
    }

    override fun onBindViewHolder(holder: StudentReportVH, position: Int) {
        val studentReport = studentReportList.get(position)

        holder.studentReportLayoutBinding.tvStudentRollNo.text = studentReport.rollNo.toString()
        holder.studentReportLayoutBinding.tvStudentName.text = studentReport.studentName
        holder.studentReportLayoutBinding.tvCourseName.text = studentReport.course.courseName
        holder.studentReportLayoutBinding.tvTotal.text = studentReport.total.toString()
        holder.studentReportLayoutBinding.tvAverage.text = studentReport.average.toString()

        var subjectDisplayAdapter = SubjectDisplayAdapter(studentReport.course.subjects)
        holder.studentReportLayoutBinding.subjectDisplayRecyclerview.layoutManager =
            LinearLayoutManager(
                mContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        holder.studentReportLayoutBinding.subjectDisplayRecyclerview.adapter = subjectDisplayAdapter


        holder.studentReportLayoutBinding.btnDelete.setOnClickListener {
            iStudentReportOperation?.deleteStudent(studentReport)
        }
    }

    override fun getItemCount(): Int {
        return studentReportList.size
    }

    fun setStudentOperation(iStudentReportOperation: IStudentReportOperation){
        this.iStudentReportOperation = iStudentReportOperation
    }

    class StudentReportVH(studentReportLayoutBinding: StudentReportLayoutBinding) : RecyclerView.ViewHolder(studentReportLayoutBinding.root){
        var studentReportLayoutBinding : StudentReportLayoutBinding

        init {
            this.studentReportLayoutBinding = studentReportLayoutBinding

        }


    }

}