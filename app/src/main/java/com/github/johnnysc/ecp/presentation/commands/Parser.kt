package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

interface Parser<T> : Mapper<String, IsEmptyHandleUseCase<T>>