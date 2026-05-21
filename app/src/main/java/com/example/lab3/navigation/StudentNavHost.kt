package com.example.lab3.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab3.screens.addexam.AddExamScreen
import com.example.lab3.screens.addstudent.AddStudentScreen
import com.example.lab3.screens.addstudentpassedexam.AddStudentPassedExamScreen
import com.example.lab3.screens.studentdetails.StudentDetailsScreen
import com.example.lab3.screens.studentlist.StudentListScreen
import com.example.lab3.StudentViewModel

@Composable
fun StudentNavHost(
    studentViewModel: StudentViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.STUDENT_LIST
    ) {
        composable(Routes.STUDENT_LIST) {
            StudentListScreen(
                students = studentViewModel.students.value,
                onAddStudentClick = {
                    navController.navigate(Routes.ADD_STUDENT)
                },
                onAddExamClick = {
                    navController.navigate(Routes.ADD_EXAM)
                },
                onStudentNameClick = { student ->
                    studentViewModel.selectStudent(student)
                    navController.navigate(Routes.STUDENT_DETAILS)
                }
            )
        }

        composable(Routes.ADD_STUDENT) {
            AddStudentScreen(
                onSaveStudent = { studentNumber, firstName, lastName, yearOfAdmission ->
                    studentViewModel.addStudent(
                        studentNumber = studentNumber,
                        firstName = firstName,
                        lastName = lastName,
                        yearOfAdmission = yearOfAdmission
                    )
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.STUDENT_DETAILS) {
            StudentDetailsScreen(
                student = studentViewModel.selectedStudent.value,
                onAddPassedExamClick = {
                    navController.navigate(Routes.ADD_STUDENT_PASSED_EXAM)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_STUDENT_PASSED_EXAM) {
            AddStudentPassedExamScreen(
                exams = studentViewModel.exams.value,
                onSavePassedExam = { exam, grade ->
                    studentViewModel.addPassedExamToSelectedStudent(
                        exam = exam,
                        grade = grade
                    )
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        //TODO: Add Exams Screen
    }
}