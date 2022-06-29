package com.github.johnnysc.ecp.sl.main

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.sl.Module
import com.github.johnnysc.ecp.presentation.main.MainViewModel

class MainModule  constructor(
    private val navigationCommunication: NavigationCommunication.Mutable,
    private val canGoBack: CanGoBack,
    private val dispatchers: Dispatchers,
    private val errorCommunication: GlobalErrorCommunication.Mutable
) : Module<MainViewModel> {
    override fun viewModel(): MainViewModel =
        MainViewModel(navigationCommunication, canGoBack, dispatchers, errorCommunication)
}