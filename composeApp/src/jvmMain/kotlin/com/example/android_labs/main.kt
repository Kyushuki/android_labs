package com.example.android_labs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.android_labs.component.RootComponentImp
import javax.swing.SwingUtilities

fun main() {
    val lifecycle = LifecycleRegistry()
    val rootComponent = runOnUiThread { RootComponentImp(DefaultComponentContext(lifecycle)) }
    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)
        Window(
            state = windowState,
            onCloseRequest = ::exitApplication,
            title = "Androidlabs",
        ) {
            App(rootComponent)
        }

    }
}

fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}