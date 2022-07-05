package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.data.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain


class WeatherViewModelChain(
    commands: List<Command<WeatherInteractor>>,
    interactor: WeatherInteractor,
    manageResources: ManageResources
) : ViewModelChain(WeatherChain(commands, interactor, manageResources))