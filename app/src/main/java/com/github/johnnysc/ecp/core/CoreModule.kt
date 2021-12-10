package com.github.johnnysc.ecp.core

import com.github.johnnysc.ecp.ui.core.NavigationCommunication

class CoreModule : ProvideNavigationCommunication {
    private val navigationCommunication = NavigationCommunication.Base()

    override fun provideNavigationCommunication() = navigationCommunication
}