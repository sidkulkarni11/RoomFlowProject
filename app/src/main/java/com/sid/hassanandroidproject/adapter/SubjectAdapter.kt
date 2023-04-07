package com.sid.hassanandroidproject.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid.hassanandroidproject.databinding.SubjectLayoutBinding
import com.sid.hassanandroidproject.model.Subject

class SubjectAdapter(subjects : List<Subject>) : RecyclerView.Adapter<SubjectAdapter.SubjectVH>() {
    var subjectList : List<Subject>

    init {
        subjectList = subjects
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectVH{
        val layoutInflater = LayoutInflater.from(parent.context)
        val subjectLayoutBinding = SubjectLayoutBinding.inflate(layoutInflater,parent,false)
        return SubjectVH(subjectLayoutBinding)
    }


    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectVH, position: Int) {
        var subject = subjectList.get(position)

        holder.subjectLayoutBinding.tvSubjectName.text = subject.subjectName



        holder.subjectLayoutBinding.edtvSubjectMarks.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val marksString = holder.subjectLayoutBinding.edtvSubjectMarks.text.toString()


                if(!marksString.isEmpty()){
                    subject.subjectMarks = marksString.toInt()
                }
            }
        })
    }

    class SubjectVH(subjectLayoutBinding: SubjectLayoutBinding) : RecyclerView.ViewHolder(subjectLayoutBinding.root){
        var subjectLayoutBinding : SubjectLayoutBinding

        init {
            this.subjectLayoutBinding = subjectLayoutBinding
        }
    }


    fun getTotal() : Int{
        var total = 0
        for (subject in subjectList){
            total  += subject.subjectMarks
        }
        return  total
    }

    fun getAvg() : Double{
        var total = 0.0
        for (subject in subjectList){
            total  += subject.subjectMarks
        }
        return  total/subjectList.size
    }
}