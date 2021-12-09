package com.github.johnnysc.ecp.ui.core

interface NavigationCommunication : Communication<NavigationUi> {
    class Base : Communication.Single<NavigationUi>(), NavigationCommunication
}