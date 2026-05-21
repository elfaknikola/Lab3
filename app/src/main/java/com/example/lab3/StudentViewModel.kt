package com.example.lab3

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lab3.data.Exam
import com.example.lab3.data.PassedExam
import com.example.lab3.data.Student

class StudentViewModel : ViewModel() {

    var students = mutableStateOf(
        listOf(
            Student(
                id = 1,
                studentNumber = "10443",
                firstName = "Marko",
                lastName = "Markovic",
                yearOfAdmission = 2023,
                passedExams = listOf(
                    PassedExam(
                        exam = Exam(1, "Matematika"),
                        grade = 9
                    ),
                    PassedExam(
                        exam = Exam(2, "Programiranje"),
                        grade = 10
                    )
                )
            ),
            Student(
                id = 2,
                studentNumber = "10458",
                firstName = "Ana",
                lastName = "Jovanovic",
                yearOfAdmission = 2023,
                passedExams = emptyList()
            )
        )
    )
        private set

    var exams = mutableStateOf(
        listOf(
            Exam(1, "Matematika"),
            Exam(2, "Programiranje"),
            Exam(3, "Baze podataka")
        )
    )
        private set

    var selectedStudent = mutableStateOf<Student?>(null)
        private set

    private var nextStudentId = 3
    private var nextExamId = 4

    fun addStudent(
        studentNumber: String,
        firstName: String,
        lastName: String,
        yearOfAdmission: Int
    ) {
        val newStudent = Student(
            id = nextStudentId,
            studentNumber = studentNumber,
            firstName = firstName,
            lastName = lastName,
            yearOfAdmission = yearOfAdmission
        )

        nextStudentId++

        students.value = students.value + newStudent
    }

    fun selectStudent(student: Student) {
        selectedStudent.value = student
    }

    //TODO: Add AddExams function

    fun addPassedExamToSelectedStudent(
        exam: Exam,
        grade: Int
    ) {
        val currentStudent = selectedStudent.value ?: return

        val updatedStudent = currentStudent.copy(
            passedExams = currentStudent.passedExams + PassedExam(
                exam = exam,
                grade = grade
            )
        )

        students.value = students.value.map { student ->
            if (student.id == updatedStudent.id) {
                updatedStudent
            } else {
                student
            }
        }

        selectedStudent.value = updatedStudent
    }
}