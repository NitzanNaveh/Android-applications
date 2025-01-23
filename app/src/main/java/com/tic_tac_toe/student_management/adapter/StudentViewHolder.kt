package com.tic_tac_toe.student_management.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tic_tac_toe.student_management.OnItemClickListener
import com.tic_tac_toe.student_management.R
import com.tic_tac_toe.student_management.model.Student

class StudentViewHolder(
    itemView: View,
    private val listener: OnItemClickListener?
) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name)
    private val idTextView: TextView = itemView.findViewById(R.id.student_row_id)
    val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)
    private val phoneTextView: TextView = itemView.findViewById(R.id.student_row_phone)
    private val addressTextView: TextView = itemView.findViewById(R.id.student_row_address)

    fun bind(student: Student?, position: Int) {
        nameTextView.text = student?.name
        idTextView.text = student?.id
        phoneTextView.text = student?.phone
        addressTextView.text = student?.address
        checkBox.isChecked = student?.isChecked ?: false

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            student?.isChecked = isChecked
        }

        itemView.setOnClickListener {
            listener?.onItemClick(position)
            listener?.onItemClick(student)
        }
    }
}
