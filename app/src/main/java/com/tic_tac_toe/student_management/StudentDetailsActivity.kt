package com.tic_tac_toe.student_management

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_details)

        val name = intent.getStringExtra("name") ?: "Unknown"
        val id = intent.getStringExtra("id") ?: "N/A"
        val phone = intent.getStringExtra("number") ?: "N/A"
        val address = intent.getStringExtra("address") ?: "N/A"
        val isChecked = intent.getBooleanExtra("isChecked", false)

        val nameTextView: TextView = findViewById(R.id.student_details_activity_name_edit_text)
        val idTextView: TextView = findViewById(R.id.student_details_activity_id)
        val phoneTextView: TextView = findViewById(R.id.student_details_activity_phone_number)
        val addressTextView: TextView = findViewById(R.id.student_details_activity_address)
        val isCheckedTextView: TextView = findViewById(R.id.student_details_activity_checkBox)
        val editButton: Button = findViewById(R.id.student_details_activity_edit_button)
        val cancelButton: Button = findViewById(R.id.student_details_activity_cancel_button)

        // Set student details
        nameTextView.text = name
        idTextView.text = id
        phoneTextView.text = phone
        addressTextView.text = address
        isCheckedTextView.text = if (isChecked) "Checked" else "Not Checked"

        cancelButton.setOnClickListener {
            finish()
        }

        editButton.setOnClickListener {
            val intent = Intent(this, StudentEditActivity::class.java).apply {
                putExtra("name", name)
                putExtra("id", id)
                putExtra("number", phone)
                putExtra("address", address)
                putExtra("isChecked", isChecked)
            }
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val deletedStudentId = data?.getStringExtra("deleted_student_id")
            if (deletedStudentId != null) {
                val resultIntent = Intent().apply {
                    putExtra("deleted_student_id", deletedStudentId)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
