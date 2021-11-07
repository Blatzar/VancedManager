package com.vanced.manager.ui.component.preference

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vanced.manager.R
import com.vanced.manager.core.preferences.CheckboxPreference
import com.vanced.manager.core.preferences.ManagerPreference
import com.vanced.manager.ui.component.button.ManagerThemedTextButton
import com.vanced.manager.ui.component.text.ManagerText
import com.vanced.manager.ui.widget.list.CheckboxItem
import kotlinx.coroutines.launch

@Composable
fun CheckboxDialogPreference(
    preferenceTitle: String,
    preference: ManagerPreference<Set<String>>,
    trailing: @Composable () -> Unit = {},
    buttons: List<CheckboxPreference>,
    onSave: (checkedButtons: List<String>) -> Unit = {}
) {
    var pref by preference
    val selectedButtons = remember { pref.toMutableStateList() }
    val coroutineScope = rememberCoroutineScope()
    DialogPreference(
        preferenceTitle = preferenceTitle,
        preferenceDescription = buttons.filter { button ->
            pref.any { selectedButton ->
                button.key == selectedButton
            }
        }.sortedBy { it.title }.joinToString(separator = ", ") { it.title },
        trailing = trailing,
        confirmButton = { isShown ->
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        isShown.value = false
                        pref = selectedButtons.toSet()
                        onSave(selectedButtons)
                    }
                }
            ) {
                ManagerText(stringResource(id = R.string.dialog_button_save))
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier.heightIn(max = 400.dp)
        ) {
            items(buttons) { button ->
                val (title, key) = button
                CheckboxItem(
                    text = title,
                    isChecked = selectedButtons.contains(key),
                    onCheck = { isChecked ->
                        if (isChecked) {
                            selectedButtons.add(key)
                        } else {
                            selectedButtons.remove(key)
                        }
                    }
                )
            }
        }
    }
}