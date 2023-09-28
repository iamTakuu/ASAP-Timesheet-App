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
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.ButtonComponent
import com.iamtakuu.asap_timesheet_app.components.DateTimePickerComponent
import com.iamtakuu.asap_timesheet_app.components.DescriptionFieldComponent
import com.iamtakuu.asap_timesheet_app.components.DropDownMenuComponent
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.components.ImageRequestButtonIcon
import com.iamtakuu.asap_timesheet_app.components.TextFieldComponent

@Composable
fun TaskCreationScreen(){
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

            Spacer(modifier = Modifier.height(10.dp))

            // Image
            ImageRequestButtonIcon(
                labelValue = stringResource(id = R.string.add_image),
                //isEnabled = taskCreationViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Category
            DropDownMenuComponent(
                labelValue = stringResource(id = R.string.category),
                dropDownItems = listOf(
                    "Option 1",
                    "Option 2",
                    "Option 3"
                ),
                onItemClick = { /*TODO*/ }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Tags
            DropDownMenuComponent(
                labelValue = stringResource(id = R.string.tags),
                dropDownItems = listOf(
                    "Tag 1",
                    "Tag 2",
                    "Tag 3",
                    "Tag 4"
                ),
                onItemClick = { /*TODO*/ }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Start Date
            DateTimePickerComponent(labelValue = stringResource(id = R.string.start_date))

            Spacer(modifier = Modifier.height(10.dp))

            // End Date
            DateTimePickerComponent(labelValue = stringResource(id = R.string.end_date))

            // Minimum Time
            TextFieldComponent(
                labelValue = stringResource(id = R.string.min_time),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    //taskCreationViewModel.onEvent(TaskCreatedEvent.MinTimeChanged(it))
                }
            )

            // Maximum Time
            TextFieldComponent(
                labelValue = stringResource(id = R.string.max_time),
                painterResource(id = R.drawable.google),
                onTextSelected = {
                    //taskCreationViewModel.onEvent(TaskCreatedEvent.MinTimeChanged(it))
                }
            )

            // Description
            DescriptionFieldComponent(
                labelValue = stringResource(id = R.string.description),
                200.dp,
                300.dp,
                onTextSelected = {
                    //taskCreationViewModel.onEvent(TaskCreatedEvent.DescriptionChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Create Task
            ButtonComponent(
                value = stringResource(id = R.string.create_task),
                onButtonClicked = {
                    //taskCreationViewModel.onEvent(TaskCreatedEvent.TaskCreationButtonClicked)
                },
                //isEnabled = taskCreationViewModel.allValidationsPassed.value
            )
        }
    }
}

@Preview
@Composable
fun PreviewTaskCreation(){
    TaskCreationScreen()
}