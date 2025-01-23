package com.tic_tac_toe.student_management

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_edit)

        val name = intent.getStringExtra("name") ?: ""
        val id = intent.getStringExtra("id") ?: ""
        val phone = intent.getStringExtra("number") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val isChecked = intent.getBooleanExtra("isChecked", false)

        val nameEditText: EditText = findViewById(R.id.student_edit_name)
        val idEditText: EditText = findViewById(R.id.student_edit_id)
        val phoneEditText: EditText = findViewById(R.id.student_edit_phone)
        val addressEditText: EditText = findViewById(R.id.student_edit_address)
        val saveButton: Button = findViewById(R.id.student_edit_update_button)
        val deleteButton: Button = findViewById(R.id.student_edit_delete_button)
        val cancelButton: Button = findViewById(R.id.student_edit_cancel_button)

        nameEditText.setText(name)
        idEditText.setText(id)
        phoneEditText.setText(phone)
        addressEditText.setText(address)

        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedId = idEditText.text.toString()
            val updatedPhone = phoneEditText.text.toString()
            val updatedAddress = addressEditText.text.toString()

            Toast.makeText(this, "Updated: $updatedName, $updatedId", Toast.LENGTH_SHORT).show()

            finish()
        }

        deleteButton.setOnClickListener {
            val intent = Intent().apply {
                putExtra("deleted_student_id", id)
            }
            setResult(RESULT_OK, intent)
            Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show()
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}
