package com.tic_tac_toe.student_management
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tic_tac_toe.student_management.adapter.StudentsRecyclerAdapter
import com.tic_tac_toe.student_management.model.Model
import com.tic_tac_toe.student_management.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentsRecyclerViewActivity : AppCompatActivity() {

    private var students: MutableList<Student>? = null
    var adapter: StudentsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.shared.students
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)
        val addStudentButton: Button = findViewById(R.id.add_student_button)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)

        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")
            }
        }

        recyclerView.adapter = adapter

        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val deletedStudentId = data?.getStringExtra("deleted_student_id")
            if (deletedStudentId != null) {
                val studentToRemove = students?.find { it.id == deletedStudentId }
                if (studentToRemove != null) {
                    students?.remove(studentToRemove)
                    adapter?.notifyDataSetChanged()
                    Log.d("StudentsRecyclerView", "Student removed: ${studentToRemove.name}")
                    Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("StudentsRecyclerView", "Student with ID $deletedStudentId not found.")
                }
                return
            }

            val updatedName = data?.getStringExtra("updated_student_name") ?: ""
            val updatedId = data?.getStringExtra("updated_student_id") ?: ""
            val updatedPhone = data?.getStringExtra("updated_student_phone") ?: ""
            val updatedAddress = data?.getStringExtra("updated_student_address") ?: ""
            val updatedCheckboxState = data?.getBooleanExtra("updated_student_isChecked", false) // Renamed variable

            if (updatedId.isNotEmpty()) {
                val studentToUpdate = students?.find { it.id == updatedId }
                if (studentToUpdate != null) {
                    if (updatedName.isNotEmpty()) studentToUpdate.name = updatedName
                    if (updatedPhone.isNotEmpty()) studentToUpdate.phone = updatedPhone
                    if (updatedAddress.isNotEmpty()) studentToUpdate.address = updatedAddress
                    studentToUpdate.isChecked = updatedCheckboxState ?: false

                    adapter?.notifyDataSetChanged()
                    Log.d("StudentsRecyclerView", "Student updated: $updatedName")
                    Toast.makeText(this, "Student Updated in List", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("StudentsRecyclerView", "Student with ID $updatedId not found.")
                }
            }
        }
    }


}
