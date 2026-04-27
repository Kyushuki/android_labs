package ui.screen

import androidlabs.composeapp.generated.resources.Res
import androidlabs.composeapp.generated.resources.about_screen_text
import androidlabs.composeapp.generated.resources.about_screen_topbar_title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowDpSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_labs.component.AboutComponent
import org.jetbrains.compose.resources.stringResource
import ui.utils.adaptiveHorizontalPadding

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(component: AboutComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.about_screen_topbar_title))
                },
            )
        },
    ) { contentPadding ->
        Column(
            Modifier.padding(contentPadding).adaptiveHorizontalPadding(
                currentWindowDpSize(),
                currentWindowAdaptiveInfo().windowSizeClass
            )
        ) {
            Text(stringResource(Res.string.about_screen_text))
        }
    }
}