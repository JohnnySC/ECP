package com.github.johnnysc.ecp.ui.core

import com.github.johnnysc.ecp.core.CoreModule
import com.github.johnnysc.ecp.data.core.BaseModule

class MainModule(private val coreModule: CoreModule) : BaseModule<MainViewModel> {

    override fun viewModel() = MainViewModel(coreModule.provideNavigationCommunication())
}
