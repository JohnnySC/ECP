package com.github.johnnysc.ecp.presentation.main.nav_screen

import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy
import com.github.johnnysc.ecp.presentation.messages.MessagesFragment

class MainNavScreen(showStrategy: ShowStrategy=ShowStrategy.ADD):NavigationScreen("mainFragment",
    MessagesFragment::class.java,showStrategy) {
}