package com.github.johnnysc.ecp.schedule.presentation.commands.myday

import com.github.johnnysc.ecp.schedule.domain.MyDayUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class MyDay : IsEmptyHandleUseCase<MyDayUseCase> {

    override suspend fun handle(useCase: MyDayUseCase): MessageUI = useCase.myDay()
}
