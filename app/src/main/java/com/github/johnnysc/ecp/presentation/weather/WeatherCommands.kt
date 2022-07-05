package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.commands.CommandAction
import com.github.johnnysc.ecp.presentation.commands.FeatureCommands

class WeatherCommands(manageResources: ManageResources) : FeatureCommands {

    override fun handle(data: String) {

    }

    override fun perform(action: CommandAction) {
        data?.let { action.perform(it) }
    }
}