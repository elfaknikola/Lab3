package com.example.lab3.screens.studentlist

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab3.data.Student

@Composable
fun StudentListScreen(
    students: List<Student>,
    onAddStudentClick: () -> Unit,
    onAddExamClick: () -> Unit,
    onStudentNameClick: (Student) -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Students",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students) { student ->
                    StudentRow(
                        student = student,
                        onStudentNameClick = onStudentNameClick
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAddStudentClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add student")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onAddExamClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add exam")
            }
        }
    }
}