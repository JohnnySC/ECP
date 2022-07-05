package com.github.johnnysc.ecp.presentation.commands

interface CommandAction {
    fun perform(data: String)
}