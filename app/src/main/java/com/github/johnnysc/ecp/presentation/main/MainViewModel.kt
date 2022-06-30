package com.github.johnnysc.ecp.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.ecp.presentation.main.nav_screen.MainNavScreen


class MainViewModel(
    private val communication: NavigationCommunication.Base,

    ) : ViewModel(), Communication.Observe<NavigationScreen> {

    init {
        communication.map(MainNavScreen())
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<NavigationScreen>) {
        communication.observe(owner, observer)
    }
}