package com.iamtakuu.asap_timesheet_app.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.ui.theme.BgColor
import com.iamtakuu.asap_timesheet_app.ui.theme.GoogleBlue
import com.iamtakuu.asap_timesheet_app.ui.theme.GoogleRed
import com.iamtakuu.asap_timesheet_app.ui.theme.MetaBlue
import com.iamtakuu.asap_timesheet_app.ui.theme.MetaDarkBlue
import com.iamtakuu.asap_timesheet_app.ui.theme.Primary
import com.iamtakuu.asap_timesheet_app.ui.theme.PurpleGrey40
import com.iamtakuu.asap_timesheet_app.ui.theme.Secondary
import com.iamtakuu.asap_timesheet_app.ui.theme.TextColor
import com.iamtakuu.asap_timesheet_app.ui.theme.componentShapes
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@Composable
// Handy component to Default text.
fun NormalTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .background(Color.White),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
// Handy component to Header text.
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(Color.White),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CheckboxComponent(
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTermsComponent(onTextSelected)
    }
}

@Composable
fun ClickableTermsComponent(onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")
                // Check if the appropriate text is being show.
                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun ClickableLoginComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText = if (tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .background(Color.White),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")
                // Check if the appropriate text is being show.
                if ((span.item == loginText)) {
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun TextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false) {

    val localFocusManager = LocalFocusManager.current
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)},
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}

@Composable
fun DescriptionFieldComponent(
    labelValue: String,
    sizeMin: Dp,
    sizeMax: Dp,
    onTextSelected: (String) -> Unit) {

    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(sizeMin, sizeMax)
            .clip(componentShapes.small),
        label = { Text(text = labelValue, Modifier.padding(0.dp, 0.dp)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = false,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        }
    )
}

@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = PurpleGrey40,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@ExperimentalMaterial3Api
@Composable
fun PasswordFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean
){
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        singleLine = true,
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)},
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImg =  if(passwordVisible.value){
                Icons.Filled.Visibility
            } else Icons.Filled.VisibilityOff

            val description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            } else{
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value}) {
                Icon(imageVector = iconImg, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else {
            PasswordVisualTransformation()
        },
        isError = !errorStatus
    )
}

@Composable
fun ButtonComponent(value: String,
                    onButtonClicked: () -> Unit,
                    isEnabled: Boolean = false
){
    Button(
        onClick = {
            onButtonClicked.invoke()
        },
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            )
            , contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ImageRequestButtonIcon(labelValue: String,
                           isEnabled: Boolean = false
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current

    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent())
    {
            uri : Uri? -> imageUri = uri
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .widthIn(80.dp)
                .heightIn(80.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Primary, Primary)),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .widthIn(60.dp)
                    .heightIn(60.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            imageUri?.let{
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                }
                else {
                    val source = ImageDecoder.createSource(context.contentResolver,it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let {  btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription =null,
                        modifier = Modifier.size(400.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GoogleSignComponent(isEnabled: Boolean = false){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(GoogleBlue, GoogleRed)),
                shape = RoundedCornerShape(25.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically){
                Image(
                    //modifier = Modifier.heightIn(),
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Sign In"
                )
                Spacer(modifier = Modifier.width(5.dp)) // Add 16dp of space
                Text(
                    text = "Google",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MetaSignComponent(isEnabled: Boolean = false){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(MetaDarkBlue, MetaBlue)),
                shape = RoundedCornerShape(25.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically){
                Image(
                    //modifier = Modifier.heightIn(),
                    painter = painterResource(id = R.drawable.meta),
                    contentDescription = "Meta Sign In"
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "Meta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(2f),
            color = PurpleGrey40,
            thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 16.sp,
            color = TextColor)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(2f),
            color = PurpleGrey40,
            thickness = 1.dp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent(
    labelValue: String
) {
    val calendarState = rememberSheetState()

    CalendarDialog(state = calendarState, selection = CalendarSelection.Date {
        date -> Log.d("Selected Date: ", "$date")
    })

    Button(onClick = {calendarState.show()},
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.CenterStart
        ){
            Text(text = labelValue,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(50.dp, 0.dp)
            )
        }
    }
}

@Composable
fun DropDownMenuComponent(
    labelValue: String,
    dropDownItems: List<String> = listOf(),
    onItemClick: (String) -> Unit,
) {
    var dropDownContextState by rememberSaveable {
        mutableStateOf(false)
    }

    var touchOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    Button(
        onClick = {
            dropDownContextState = true
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .pointerInput(true) {
                detectTapGestures(
                    onPress = {
                        touchOffset = DpOffset(it.x.toDp(), it.y.toDp())
                    }
                )
            }
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            },
        contentPadding = PaddingValues(0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.CenterStart
        ){
            Text(text = labelValue,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(50.dp, 0.dp)
            )
        }

        DropdownMenu(
            expanded = dropDownContextState,
            onDismissRequest = { dropDownContextState = false },
            offset = touchOffset.copy(
                y = touchOffset.y - itemHeight
            )
        ) {
            dropDownItems.forEach{ item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onItemClick(item)
                        dropDownContextState = false
                    }
                )
            }
        }
    }
}