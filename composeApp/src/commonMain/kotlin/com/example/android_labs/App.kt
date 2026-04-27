package com.example.android_labs

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidlabs.composeapp.generated.resources.Res
import androidlabs.composeapp.generated.resources.about_screen_topbar_title
import androidlabs.composeapp.generated.resources.home_screen_topbar_title
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItem
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.window.core.layout.WindowSizeClass
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.android_labs.component.Config
import com.example.android_labs.component.RootComponent
import org.jetbrains.compose.resources.stringResource
import ui.screen.AboutScreen
import ui.screen.HomeScreen
import ui.theme.getApplicationColorScheme



@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
fun App(rootComponent: RootComponent) {
    MaterialTheme(colorScheme = getApplicationColorScheme()) {
        val navigationSuiteType = with(currentWindowAdaptiveInfo()) {
            when {
                windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) ->
                    NavigationSuiteType.WideNavigationRailExpanded

                windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) ->
                    NavigationSuiteType.WideNavigationRailCollapsed

                else -> NavigationSuiteType.ShortNavigationBarCompact
            }
        }

        val navigationScaffoldState = rememberNavigationSuiteScaffoldState()

        val childStack by rootComponent.childStack.subscribeAsState()

        NavigationSuiteScaffold(

            navigationItems = {
                NavigationSuiteItem(
                    selected = childStack.active.configuration == Config.Home,
                    onClick = { rootComponent.navigate(Config.Home) },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text(stringResource(Res.string.home_screen_topbar_title)) },
                    navigationSuiteType = navigationSuiteType
                )
                NavigationSuiteItem(
                    selected = childStack.active.configuration == Config.About,
                    onClick = { rootComponent.navigate(Config.About) },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text(stringResource(Res.string.about_screen_topbar_title)) },
                    navigationSuiteType = navigationSuiteType
                )
            },

            navigationSuiteType = navigationSuiteType,

            state = navigationScaffoldState
        ) {
            Children(rootComponent.childStack, animation = stackAnimation(fade())) {
                when (val child = it.instance) {
                    is RootComponent.Child.Home -> HomeScreen(child.component)
                    is RootComponent.Child.About -> AboutScreen(child.component)
                }
            }
        }
    }
}

