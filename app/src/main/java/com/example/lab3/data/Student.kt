package com.example.lab3.data


data class Student(
    val id: Int,
    val studentNumber: String,
    val firstName: String,
    val lastName: String,
    val yearOfAdmission: Int,
    val passedExams: List<PassedExam> = emptyList()
){
    val averageGrade: Double
        get() = if (passedExams.isEmpty()) {
            0.0
        } else {
            passedExams.map { it.grade }.average()
        }
}