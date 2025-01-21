package com.tic_tac_toe.student_management.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object { // singleton object
        val shared = Model()
    }

    init {
        for ( i in 0 until 10) {
            val student = Student(
                name = "Name $i",
                id = "Student ID $i",
                avatarUrl = "",
                isChecked = false
            )
            students.add(student)
        }
    }
}