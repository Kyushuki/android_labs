package com.example.android_labs.component

import com.arkivanov.decompose.ComponentContext

interface HomeComponent {
}

class HomeComponentImp(componentContext: ComponentContext) : HomeComponent, ComponentContext by componentContext {}