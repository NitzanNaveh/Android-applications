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
    ):

    RecyclerView.ViewHolder(itemView) {
    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var checkBox: CheckBox? = null
    private var student: Student? = null
    private var phoneTextView: TextView? = null
    private var address: TextView? = null

    init {
        phoneTextView = itemView.findViewById(R.id.student_row_phone)
        nameTextView = itemView.findViewById(R.id.student_row_name)
        idTextView = itemView.findViewById(R.id.student_row_id)
        checkBox = itemView.findViewById(R.id.student_row_check_box)
        address = itemView.findViewById(R.id.student_row_address)


        checkBox?.apply {
            setOnClickListener { view ->
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                }
            }
        }

        itemView.setOnClickListener {
        }
        listener?.onItemClick(adapterPosition)
        listener?.onItemClick(student)
    }

    fun bind(student: Student?, position: Int) {
        this.student = student
        nameTextView?.text = student?.name
        idTextView?.text = student?.id
        phoneTextView?.text = student?.phone
        address?.text = student?.address

        checkBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position

            setOnCheckedChangeListener { _, isChecked ->
                // Update the student's `isChecked` state in the shared model
                student?.isChecked = isChecked
            }
        }
    }
}