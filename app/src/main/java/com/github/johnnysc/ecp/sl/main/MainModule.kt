package com.github.johnnysc.ecp.sl.main

import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.Module
import com.github.johnnysc.ecp.presentation.main.MainViewModel

class MainModule(
    private val navigationCommunication: NavigationCommunication.Base,
    private val canGoBack: CanGoBack.Callback
) : Module<MainViewModel> {
    override fun viewModel(): MainViewModel =
        MainViewModel(navigationCommunication, canGoBack)
}