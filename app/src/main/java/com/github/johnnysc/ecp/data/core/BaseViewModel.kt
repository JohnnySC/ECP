package com.github.johnnysc.ecp.data.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.johnnysc.ecp.ui.core.Communication
import com.github.johnnysc.ecp.ui.core.NavigationCommunication
import com.github.johnnysc.ecp.ui.core.NavigationUi
import com.github.johnnysc.ecp.ui.core.Observe

abstract class BaseViewModel<T>(
    protected val navigation: NavigationCommunication,
    protected val communication: Communication<T>,
) : ViewModel(),
    Observe<T> {
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
        communication.observe(owner, observer)

    fun init() {
        navigation.map(NavigationUi())
    }
}