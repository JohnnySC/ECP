package com.github.johnnysc.ecp.presentation.commands

interface FeatureCommands {

    fun handle(data: String)

    fun perform(action: CommandAction)
}