package com.tic_tac_toe.student_management

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
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
        val updateButton: Button = findViewById(R.id.student_edit_update_button)
        val deleteButton: Button = findViewById(R.id.student_edit_delete_button)
        val cancelButton: Button = findViewById(R.id.student_edit_cancel_button)
        val checkBox: CheckBox = findViewById(R.id.student_edit_checkBox)
        checkBox.isChecked = isChecked


        nameEditText.setText(name)
        idEditText.setText(id)
        phoneEditText.setText(phone)
        addressEditText.setText(address)

        updateButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedId = idEditText.text.toString()
            val updatedPhone = phoneEditText.text.toString()
            val updatedAddress = addressEditText.text.toString()
            val updatedIsChecked = checkBox.isChecked

            val intent = Intent().apply {
                putExtra("updated_student_name", updatedName)
                putExtra("updated_student_id", updatedId)
                putExtra("updated_student_phone", updatedPhone)
                putExtra("updated_student_address", updatedAddress)
                putExtra("updated_student_isChecked", updatedIsChecked)
            }

            setResult(RESULT_OK, intent)
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
