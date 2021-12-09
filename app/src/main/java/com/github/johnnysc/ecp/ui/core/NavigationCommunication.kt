package com.github.johnnysc.ecp.ui.core

import com.github.johnnysc.ecp.ui.NavigationUi

interface NavigationCommunication : Communication<NavigationUi> {
    class Base : Communication.Single<NavigationUi>(), NavigationCommunication
}