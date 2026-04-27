package com.example.android_labs.component

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface RootComponent {
    val childStack: Value<ChildStack<Config, Child>>

    fun navigate(config: Config.MainScreen)

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class About(val component: AboutComponent) : Child
    }
}

class RootComponentImp(
    componentContext: ComponentContext
): RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override fun navigate(config: Config.MainScreen) {
        navigation.bringToFront(config)
    }

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = { cfg, ctx ->
            when (cfg) {
                is Config.Home -> RootComponent.Child.Home(HomeComponentImp(componentContext = ctx))
                is Config.About -> RootComponent.Child.About(AboutComponentImp(ctx))
            }

        }
    )
}

@Serializable
sealed interface Config {
    @Serializable
    data object Home: Config, MainScreen
    @Serializable

    data object About: Config, MainScreen
    sealed interface MainScreen: Config
}
