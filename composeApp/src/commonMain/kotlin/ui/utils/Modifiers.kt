package ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

fun Modifier.adaptive(windowSizeClass: WindowSizeClass): Modifier {
    val width = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> windowSizeClass.minWidthDp / 2
        else -> return this.fillMaxWidth()
    }
    return this.width(width.dp)
}