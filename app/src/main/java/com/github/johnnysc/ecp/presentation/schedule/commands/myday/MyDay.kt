package com.github.johnnysc.ecp.presentation.schedule.commands.myday

import com.github.johnnysc.ecp.domain.schedule.MyDayUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class MyDay : IsEmptyHandleUseCase<MyDayUseCase> {

    override suspend fun handle(useCase: MyDayUseCase): MessageUI = useCase.myDay()
}
