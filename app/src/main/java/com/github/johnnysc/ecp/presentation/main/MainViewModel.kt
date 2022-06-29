package com.github.johnnysc.ecp.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.ecp.presentation.main.nav_screen.MainNavScreen


class MainViewModel(
    private val navigationCommunication: NavigationCommunication.Mutable,
    canGoBack: CanGoBack,
    dispatchers: Dispatchers,
    communication: GlobalErrorCommunication.Mutable
) : BackPress.Activity.ViewModel<String>(canGoBack, communication, dispatchers) {

    init {
        navigationCommunication.map(MainNavScreen())
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        navigationCommunication.observe(owner, observer)
    }
}