package com.sid.hassanandroidproject.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid.hassanandroidproject.databinding.SubjectDisplayLayoutBinding
import com.sid.hassanandroidproject.databinding.SubjectLayoutBinding
import com.sid.hassanandroidproject.model.Subject

class SubjectDisplayAdapter(subjects : List<Subject>) : RecyclerView.Adapter<SubjectDisplayAdapter.SubjectVH>() {
    var subjectList : List<Subject>

    init {
        subjectList = subjects
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectVH{
        val layoutInflater = LayoutInflater.from(parent.context)
        val subjectDisplayLayoutBinding = SubjectDisplayLayoutBinding.inflate(layoutInflater,parent,false)
        return SubjectVH(subjectDisplayLayoutBinding)
    }


    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectVH, position: Int) {
        var subject = subjectList.get(position)

        holder.subjectLayoutBinding.tvSubjectName.text = subject.subjectName

        holder.subjectLayoutBinding.tvSubjectMarks.text = subject.subjectMarks.toString()


    }

    class SubjectVH(subjectLayoutBinding: SubjectDisplayLayoutBinding) : RecyclerView.ViewHolder(subjectLayoutBinding.root){
        var subjectLayoutBinding : SubjectDisplayLayoutBinding

        init {
            this.subjectLayoutBinding = subjectLayoutBinding
        }
    }



}