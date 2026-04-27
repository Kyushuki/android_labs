package com.example.android_labs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import androidlabs.composeapp.generated.resources.Res
import androidlabs.composeapp.generated.resources.about_screen_topbar_title
import androidlabs.composeapp.generated.resources.addbtn
import androidlabs.composeapp.generated.resources.addition
import androidlabs.composeapp.generated.resources.app_name
import androidlabs.composeapp.generated.resources.cancel
import androidlabs.composeapp.generated.resources.compose_multiplatform
import androidlabs.composeapp.generated.resources.confirm
import androidlabs.composeapp.generated.resources.deleteitem_text
import androidlabs.composeapp.generated.resources.deleteitem_title
import androidlabs.composeapp.generated.resources.example
import androidlabs.composeapp.generated.resources.home_screen_topbar_title
import androidlabs.composeapp.generated.resources.lab_name
import androidlabs.composeapp.generated.resources.lab_num
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItem
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.android_labs.component.Config
import com.example.android_labs.component.RootComponent
import jdk.jfr.Description
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import ui.screen.AboutScreen
import ui.screen.HomeScreen
import ui.theme.getApplicationColorScheme
import ui.utils.adaptive


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

