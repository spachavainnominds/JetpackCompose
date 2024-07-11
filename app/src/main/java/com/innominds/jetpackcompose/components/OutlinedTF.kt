package com.innominds.jetpackcompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    label: String,
    hint: String = "",
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = valueState.value.toString(),
        onValueChange = { value -> valueState.value = value },
        label = { Text(label) },
        placeholder = { Text(hint) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(74, 20, 140),
            unfocusedBorderColor = Color(33, 33, 33)
        ),

        singleLine = isSingleLine,
        enabled = enabled,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AttachMoney,
                contentDescription = "Dollar Logo",
                tint = Color(33, 33, 33)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Clear,
                contentDescription = "Clear Logo",
                tint = Color(33, 33, 33)
            )
        },
//        ImeAction = ImeAction,
//        impAction = impAction,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        )
}
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit,
    onNavIconClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavIconClick) {
                Icon(Icons.Default.AddHomeWork, contentDescription = null)
            }
        },
        actions = actions
    )
}*/
