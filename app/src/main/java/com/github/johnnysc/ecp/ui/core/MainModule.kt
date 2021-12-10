package com.github.johnnysc.ecp.ui.core

import com.github.johnnysc.ecp.data.core.BaseModule

class MainModule : BaseModule<MainViewModel> {
    private var communication = NavigationCommunication.Base()

    override fun viewModel() = MainViewModel(communication)
}
