package com.github.johnnysc.ecp.ui

import com.github.johnnysc.ecp.ui.core.Communication

interface NavigationCommunication : Communication<NavigationUi> {
    class Base : Communication.Single<NavigationUi>(), NavigationCommunication
}