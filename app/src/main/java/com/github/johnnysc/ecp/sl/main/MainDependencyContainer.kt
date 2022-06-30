package com.github.johnnysc.ecp.sl.main

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.ecp.presentation.main.MainViewModel

class MainDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == MainViewModel::class.java)
            MainModule(
                NavigationCommunication.Base()
            )
        else
            dependencyContainer.module(clazz)


}