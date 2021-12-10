package com.github.johnnysc.ecp.core

import com.github.johnnysc.ecp.ui.core.NavigationCommunication

interface ProvideNavigationCommunication {
    fun provideNavigationCommunication(): NavigationCommunication
}