package com.john.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.john.androidapp.data.CheckInfo
import com.john.androidapp.ui.theme.AndroidappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val myOptions = getOptions(titles = listOf("Option 1", "Option 2", "Option 3"))
//                    Column {
//                        myOptions.forEach {
//                            MyListOfCheckBox(it)
//                        }
//                    }
//                    var show by remember {
//                        mutableStateOf(false)
//                    }
//
//                    fun onDismiss() {
//                        show = false
//                    }
//
//                    fun onConfirm() {
//                        show = false
//                    }
//
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        Button(onClick = { show = true }) {
//                            Text(text = "Show dialog")
//                        }
//                        MyAlertDialog(show = show, onConfirm = { onConfirm() }, onDismiss = { onDismiss() })
//                    }
                    MyConfirmationDialog()
                }
            }
        }
    }
}

@Composable
fun MyConfirmationDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card(shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp))
            {
                MyTitleDialog(text = "Phone ringtone")
                Divider(Modifier.fillMaxWidth(), color = Color.LightGray)
                MyRadioButton()
                Divider(Modifier.fillMaxWidth(), color = Color.LightGray)
                Row(Modifier.align(Alignment.End)) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Cancel")
                    }

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}

class Profile(private val email: String?, private val avatar: Int) {
    fun getEmail(): String? {
        return email
    }

    fun getAvatar(): Int {
        return avatar
    }
}


@Composable
fun MyAdvancedCustomDialog() {

    val profileExample = Profile(email = "test@example.com", avatar = R.drawable.avatar)

    val profiles = listOf(profileExample)

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            MyTitleDialog(text = "Set backup account")

            profiles.forEach { profile ->
                AccountItem(email = profile.getEmail() ?: "", drawable = profile.getAvatar())
            }

            AccountItem(email = "Add account", drawable = R.drawable.add )
        }
    }
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )

        Text(
            text = email,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .padding(start = 8.dp)
        )
    }
}

@Composable
fun MyTitleDialog(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun MySimpleCustomDialog() {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Text(text = "This is a example")
        }
    }
}

@Composable
fun MyAlertDialog(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = {

            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Dismiss")
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirm")
                }
            },
            title = { Text(text = "Title") }, text = { Text(text = "Description") },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRangeSlider() {
    var currentRange by remember { mutableStateOf(0f..10f) }
    RangeSlider(
        value = currentRange,
        onValueChange = { currentRange = it },
        valueRange = 0f..10f,
        steps = 9
    )

    Text(text = "${currentRange.start} - ${currentRange.endInclusive}")
}

@Composable
fun MyAdvancedSlider() {
    var sliderPosition by remember {
        mutableStateOf(0f)
    }

    var completeValue by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { completeValue = sliderPosition.toString() },
            modifier = Modifier.fillMaxWidth(),
            valueRange = 0f..100f,
            steps = 9,
        )
        Text(text = completeValue)
    }
}


@Composable
fun MySlider() {
    var sliderPosition by remember {
        mutableStateOf(0f)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = sliderPosition.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu() {
    var selectedText by remember {
        mutableStateOf("")
    }
    var expanded by remember { mutableStateOf(false) }

    val desserts = listOf<String>("Ice Cream", "Chocolate", "Coffe")

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(
                    text = { Text(text = dessert) },
                    onClick = { selectedText = dessert; expanded = false })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    BadgedBox(
        modifier = Modifier.padding(2.dp),
        badge = {
            Badge(
                content = {
                    Text(modifier = Modifier.padding(2.dp), text = "10")
                },
                contentColor = Color.White,
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.Cyan)
            )
        },
    ) {
        Icon(
            modifier = Modifier.size(2.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "l",
            tint = Color.Blue
        )
    }
}

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    )
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )

    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Title")
            Text(text = "Description")
        }
    }
}

@Composable
fun MyRadioButton() {
    var selectedOption by rememberSaveable { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")
    Column {
        options.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { selectedOption = text }
                )
                Text(text = text)
            }
        }
    }
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { status = it }
        )
    }
}

@Composable
fun MyTriStatusCheckkBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Indeterminate) }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun MyListOfCheckBox(checkInfo: CheckInfo) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = checkInfo.onCheckedChange
        )
        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckboxWithText() {
    var isChecked by rememberSaveable { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Green
            )
        )
        Text(text = "Checkbox")
    }
}

@Composable
fun MyCheckBox() {
    var isChecked by rememberSaveable { mutableStateOf(false) }

    Checkbox(checked = isChecked, onCheckedChange = { isChecked = !isChecked })
}

@Composable
fun MySwitch() {
    var isChecked by rememberSaveable { mutableStateOf(false) }

    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = !isChecked },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Red,
            uncheckedThumbColor = Color.Green,
            checkedTrackColor = Color.Blue,
            uncheckedTrackColor = Color.Magenta
        )
    )
}

@Composable
fun MyProgressAdvance() {
    var progressValue by rememberSaveable { mutableStateOf(0.5f) }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progressValue)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(onClick = { progressValue += 0.1f }) {
                Text(text = "Increment")
            }

            Button(onClick = { progressValue -= 0.1f }) {
                Text(text = "Decrement")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cambiar estado")
        }
//        LinearProgressIndicator()
    }
}


@Composable
fun MyStateExample() {
    var counter by rememberSaveable { mutableStateOf(0) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            counter++
        }) {
            Text(text = "Pulsar")
        }
        Text(text = "I am pulsed $counter times")
    }
}

@Composable
fun MyComplexLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Ejemplo 1")
        }
        MySpacer(size = 30)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo 2")
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo 3")
            }
        }
        Box(
            modifier = Modifier
                .background(Color.Magenta)
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Ejemplo 4")
        }
    }

}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun MyColumn() {
    // Column is the same of Stack but in horizontal is Row Composable
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            "Example 1", modifier = Modifier
                .background(Color.Blue)
                .weight(1f)
        )
        Text(
            "Example 1", modifier = Modifier
                .background(Color.Yellow)
                .weight(2f)
        )
        Text(
            "Example 1", modifier = Modifier
                .background(Color.Green)
                .weight(1f)
        )
    }
}

@Composable
fun MyBox(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Cyan)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Hello $name")
        }
    }
}