package com.github.johnnysc.ecp.presentation.schedule.commands.myschedule

import com.github.johnnysc.ecp.domain.schedule.MyScheduleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase

class MySchedule : IsEmptyHandleUseCase<MyScheduleUseCase> {

    override suspend fun handle(useCase: MyScheduleUseCase): MessageUI = useCase.mySchedule()
}
