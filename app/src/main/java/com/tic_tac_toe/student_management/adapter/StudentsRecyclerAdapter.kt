package com.tic_tac_toe.student_management.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tic_tac_toe.student_management.OnItemClickListener
import com.tic_tac_toe.student_management.R
import com.tic_tac_toe.student_management.StudentDetailsActivity
import com.tic_tac_toe.student_management.model.Student

class StudentsRecyclerAdapter(private val students: List<Student>?) : RecyclerView.Adapter<StudentViewHolder>() {

    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view, listener)
    }

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students?.get(position)
        holder.bind(student, position)

        // Set up click listener to navigate to StudentDetails
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StudentDetailsActivity::class.java).apply {
                putExtra("name", student?.name)
                putExtra("id", student?.id)
                putExtra("number", student?.phone)
                putExtra("address", student?.address)
                putExtra("isChecked", student?.isChecked ?: false)
            }
            (holder.itemView.context as AppCompatActivity).startActivityForResult(intent, 1)
        }
    }
}
