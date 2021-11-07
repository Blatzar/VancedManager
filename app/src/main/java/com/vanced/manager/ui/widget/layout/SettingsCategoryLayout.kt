package com.vanced.manager.ui.widget.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SettingsCategoryLayout(
    categoryName: String,
    content: @Composable () -> Unit
) {
    CategoryLayout(
        categoryName = categoryName,
    ) {
        Column {
            content()
        }
    }
}