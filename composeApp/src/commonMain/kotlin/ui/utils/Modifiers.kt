package ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

fun Modifier.adaptive(windowSizeClass: WindowSizeClass): Modifier {
    val width = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> windowSizeClass.minWidthDp / 2
        else -> return this.fillMaxWidth()
    }
    return this.width(width.dp)
}

const val EXPANDED_HORIZONTAL_PADDING = WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND / 4
const val COMPACT_HORIZONTAL_PADDING = 4

fun Modifier.adaptiveHorizontalPadding(
    windowSize: DpSize,
    windowSizeClass: WindowSizeClass,
): Modifier {
    val paddingDp = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> EXPANDED_HORIZONTAL_PADDING.toFloat()
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            val factor = (windowSize.width.value - WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) /
                    (WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND - WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
            COMPACT_HORIZONTAL_PADDING * (1 - factor) + EXPANDED_HORIZONTAL_PADDING * factor
        }

        else -> COMPACT_HORIZONTAL_PADDING.toFloat()
    }
    return this.padding(horizontal = paddingDp.dp)
}