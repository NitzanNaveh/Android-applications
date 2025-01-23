package com.tic_tac_toe.student_management.model

data class Student(
    var name: String,
    val id: String,
    var phone: String,
    var address: String,
    val avatarUrl: String,
    var isChecked: Boolean = false
)
