package com.tic_tac_toe.student_management.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object { // singleton object
        val shared = Model()
    }

    fun add(student: Student) {
        students.add(student)
    }
}