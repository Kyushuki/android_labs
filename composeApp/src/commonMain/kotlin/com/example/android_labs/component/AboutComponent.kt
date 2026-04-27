package com.example.android_labs.component

import com.arkivanov.decompose.ComponentContext

interface AboutComponent

class AboutComponentImp(componentContext: ComponentContext) : AboutComponent, ComponentContext by componentContext