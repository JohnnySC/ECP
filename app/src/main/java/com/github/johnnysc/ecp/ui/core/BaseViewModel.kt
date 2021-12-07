package com.github.johnnysc.ecp.ui.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>(
    protected val communication: Communication<T>
) : ViewModel(), Observe<T> {
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
        communication.observe(owner, observer)
}