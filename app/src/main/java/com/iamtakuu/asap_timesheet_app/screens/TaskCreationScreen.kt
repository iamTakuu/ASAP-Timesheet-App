package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.ButtonComponent
import com.iamtakuu.asap_timesheet_app.components.DescriptionFieldComponent
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.components.TextFieldComponent
import com.iamtakuu.asap_timesheet_app.data.login.TaskCreatedEvent
import com.iamtakuu.asap_timesheet_app.data.login.TaskCreationViewModel

@Composable
fun TaskCreationScreen(taskCreationViewModel: TaskCreationViewModel = viewModel()){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Heading
            HeadingTextComponent(value = stringResource(id = R.string.task_details))

            Spacer(modifier = Modifier.height(20.dp))



            Spacer(modifier = Modifier.height(20.dp))

            // Start Date
            TextFieldComponent(
                labelValue = stringResource(id = R.string.start_date),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.StartDateChanged(it))
                }
            )

            // End Date
            TextFieldComponent(
                labelValue = stringResource(id = R.string.end_date),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.EndDateChanged(it))
                }
            )

            // Time
            TextFieldComponent(
                labelValue = stringResource(id = R.string.time),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.TimeChanged(it.toInt()))
                }
            )

            // Category
            TextFieldComponent(
                labelValue = stringResource(id = R.string.category),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.CategoryChanged(it))
                }
            )

            // Tags
            TextFieldComponent(
                labelValue = stringResource(id = R.string.tags),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.TagsChanged(it))
                }
            )

            // Description
            DescriptionFieldComponent(
                labelValue = stringResource(id = R.string.description),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.DescriptionChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Create Task
            ButtonComponent(
                value = stringResource(id = R.string.create_task),
                onButtonClicked = {
                    taskCreationViewModel.onEvent(TaskCreatedEvent.TaskCreationButtonClicked)
                },
                isEnabled = taskCreationViewModel.allValidationsPassed.value)
        }
    }
}

@Preview
@Composable
fun PreviewTaskCreation(){
    TaskCreationScreen()
}