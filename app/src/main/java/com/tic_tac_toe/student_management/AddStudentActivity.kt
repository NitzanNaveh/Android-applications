package com.tic_tac_toe.student_management

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tic_tac_toe.student_management.model.Model
import com.tic_tac_toe.student_management.model.Student

class AddStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var saveMessageTextView: TextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private val model = Model.shared.students

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Initialize views
        nameEditText = findViewById(R.id.add_student_activity_name_edit_text)
        idEditText = findViewById(R.id.add_student_activity_id)
        phoneNumberEditText = findViewById(R.id.add_student_activity_phone_number)
        addressEditText = findViewById(R.id.add_student_activity_address)
        checkBox = findViewById(R.id.add_student_activity_checkBox)
        saveMessageTextView = findViewById(R.id.save_message_text_view)
        saveButton = findViewById(R.id.add_student_activity_save_button)
        cancelButton = findViewById(R.id.add_student_activity_cancel_button)

        // Set listeners
        cancelButton.setOnClickListener { finish() }
        saveButton.setOnClickListener { handleAddStudent() }
    }

    private fun handleAddStudent() {
        val name = nameEditText.text.toString().trim()
        val id = idEditText.text.toString().trim()
        val phone = phoneNumberEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val isChecked = checkBox.isChecked

        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            saveMessageTextView.text = "All fields are required!"
            return
        }

        val student = Student(name, id, phone, address, avatarUrl = "", isChecked)
        model.add(student)

        saveMessageTextView.text = "Student $name with ID $id saved!"
        finish()
    }
}
