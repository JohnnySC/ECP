package com.github.johnnysc.ecp.ui.core

interface ProgressCommunication : Communication<Boolean> {
    class Base : Communication.Base<Boolean>(), ProgressCommunication
}