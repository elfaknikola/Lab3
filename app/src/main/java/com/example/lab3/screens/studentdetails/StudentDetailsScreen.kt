package com.example.lab3.screens.studentdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab3.data.Student

@Composable
fun StudentDetailsScreen(
    student: Student?,
    onAddPassedExamClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Student details",
            style = MaterialTheme.typography.headlineMedium
        )

        if (student == null) {
            Text("Student not selected.")

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }

            return@Column
        }

        Text("Student number: ${student.studentNumber}")
        Text("First name: ${student.firstName}")
        Text("Last name: ${student.lastName}")
        Text("Year of admission: ${student.yearOfAdmission}")

        if (student.averageGrade == 0.0) {
            Text("Average grade: No passed exams")
        } else {
            Text("Average grade: ${String.format("%.2f", student.averageGrade)}")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Passed exams",
            style = MaterialTheme.typography.titleMedium
        )

        if (student.passedExams.isEmpty()) {
            Text("This student has no passed exams.")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(student.passedExams) { passedExam ->
                    PassedExamRow(passedExam = passedExam)
                }
            }
        }

        Button(
            onClick = onAddPassedExamClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add passed exam")
        }

        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}