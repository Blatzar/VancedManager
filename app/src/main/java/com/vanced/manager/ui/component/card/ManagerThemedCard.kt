package com.vanced.manager.ui.component.card

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.vanced.manager.ui.theme.MediumShape

@Composable
fun ManagerContainerCard(
    modifier: Modifier = Modifier,
    shape: Shape = MediumShape,
    content: @Composable () -> Unit,
) {
    ManagerCard(
        modifier = modifier,
        shape = shape,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        content = content
    )
}

@Composable
fun ManagerContainerCard(
    modifier: Modifier = Modifier,
    shape: Shape = MediumShape,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    ManagerCard(
        modifier = modifier,
        shape = shape,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        onClick = onClick,
        content = content,
    )
}