package com.github.johnnysc.ecp.presentation.main

import androidx.fragment.app.FragmentManager
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.ecp.presentation.main.nav_screen.MainNavScreen

class BaseFragmentFactory (
    containerId: Int,
    fragmentManager: FragmentManager,
) : FragmentFactory.Abstract(
    containerId,
    fragmentManager,
) {

    override val screens: List<NavigationScreen> = listOf(MainNavScreen())

}