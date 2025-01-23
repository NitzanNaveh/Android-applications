package com.tic_tac_toe.student_management

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
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
        val checkBox: CheckBox = findViewById(R.id.student_details_activity_checkBox)
        val editButton: Button = findViewById(R.id.student_details_activity_edit_button)
        val cancelButton: Button = findViewById(R.id.student_details_activity_cancel_button)

        nameTextView.text = name
        idTextView.text = id
        phoneTextView.text = phone
        addressTextView.text = address
        checkBox.isChecked = isChecked

        cancelButton.setOnClickListener {
            val updatedIsChecked = checkBox.isChecked
            val intent = Intent().apply {
                putExtra("updated_student_id", id)
                putExtra("updated_student_isChecked", updatedIsChecked)
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        editButton.setOnClickListener {
            val intent = Intent(this, StudentEditActivity::class.java).apply {
                putExtra("name", name)
                putExtra("id", id)
                putExtra("number", phone)
                putExtra("address", address)
                putExtra("isChecked", checkBox.isChecked)
            }
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val deletedStudentId = data?.getStringExtra("deleted_student_id")
            if (deletedStudentId != null) {
                val intent = Intent().apply {
                    putExtra("deleted_student_id", deletedStudentId)
                }
                setResult(RESULT_OK, intent)
                finish()
                return
            }

            val updatedName = data?.getStringExtra("updated_student_name")
            val updatedId = data?.getStringExtra("updated_student_id")
            val updatedPhone = data?.getStringExtra("updated_student_phone")
            val updatedAddress = data?.getStringExtra("updated_student_address")
            val updatedIsChecked = data?.getBooleanExtra("updated_student_isChecked", false)

            if (updatedName != null && updatedId != null) {
                val intent = Intent().apply {
                    putExtra("updated_student_name", updatedName)
                    putExtra("updated_student_id", updatedId)
                    putExtra("updated_student_phone", updatedPhone)
                    putExtra("updated_student_address", updatedAddress)
                    putExtra("updated_student_isChecked", updatedIsChecked)
                }
                setResult(RESULT_OK, intent) // Pass updated student data back
                finish()
            }
        }
    }
}
