package com.iamtakuu.asap_timesheet_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.components.HeadingTextComponent
import com.iamtakuu.asap_timesheet_app.ui.theme.Primary
import com.iamtakuu.asap_timesheet_app.ui.theme.Secondary

@Composable
fun CategoriesScreen(
    categories: List<String> = listOf("University", "Internship", "Personal"),
    icons: List<ImageVector> = listOf(Icons.Default.Timer, Icons.Default.Savings, Icons.Default.LaptopChromebook)
) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        color = Color.White
    ) {
        HeadingTextComponent(value = stringResource(id = R.string.categories))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 64.dp)
        ) {
            categories.zip(icons).forEach { pair ->
                Box (
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {
                    Image(
                        imageVector = pair.component2(),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp, 8.dp),
                        alignment = Alignment.Center,
                        colorFilter = ColorFilter.tint(Color.White)
                    )

                    Text(
                        text = pair.component1(),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 8.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCategories() {
    CategoriesScreen()
}