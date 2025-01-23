package com.tic_tac_toe.student_management.model

data class Student(
    val name: String,
    val id: String,
    val phone: String,
    val address: String,
    val avatarUrl: String,
    var isChecked: Boolean = false
)
