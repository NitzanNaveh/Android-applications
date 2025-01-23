package com.tic_tac_toe.student_management.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tic_tac_toe.student_management.R
import com.tic_tac_toe.student_management.StudentDetails
import com.tic_tac_toe.student_management.OnItemClickListener
import com.tic_tac_toe.student_management.model.Student

class StudentsRecyclerAdapter(private val students: List<Student>?) : RecyclerView.Adapter<StudentViewHolder>() {

    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view, listener)
    }

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students?.get(position), position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StudentDetails::class.java)
            intent.putExtra("name", students?.get(position)?.name)
            intent.putExtra("id", students?.get(position)?.id)
            intent.putExtra("number", students?.get(position)?.phone)
            intent.putExtra("address", students?.get(position)?.address)
            intent.putExtra("isChecked", students?.get(position)?.isChecked ?: false)
            holder.itemView.context.startActivity(intent)
        }
    }
}