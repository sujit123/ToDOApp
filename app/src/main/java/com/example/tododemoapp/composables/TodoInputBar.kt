package com.example.tododemoapp.composables

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tododemoapp.MainActivity
import com.example.tododemoapp.R
import com.example.tododemoapp.ui.constants.*

@Composable
fun TodoInputBar(
    modifier: Modifier = Modifier,
    onAddButtonClick: (String) -> Unit = {}
) {
    val input = remember { mutableStateOf("") }
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        TextField(
            modifier = Modifier,
            textStyle = TodoInputBarTextStyle,
            value = input.value,
            onValueChange = { newText -> input.value = newText },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.todo_input_bar_hint),
                    style = TodoInputBarTextStyle.copy(color = Color.Black.copy(alpha = 0.5f))
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            )
        )

        Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = {
            if (input.value.trim().isEmpty()) {
                Toast.makeText(ctx, "Please Add TODO", Toast.LENGTH_LONG).show()
            } else if (input.value.trim() == "Error") {
                Toast.makeText(ctx, "Failed to add TODO", Toast.LENGTH_LONG).show()
                ctx.startActivity(Intent(ctx, MainActivity::class.java))
            } else {
                onAddButtonClick(input.value)
                input.value = ""
                ctx.startActivity(Intent(ctx, MainActivity::class.java))
            }
        }) {
            Text(text = "ADD TODO")
        }
    }
}

@Preview
@Composable
fun TodoInputBarPreview() {
    TodoInputBar()
}