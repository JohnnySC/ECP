package com.github.johnnysc.ecp.domain.schedule

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface MyScheduleUseCase {

    suspend fun mySchedule(): MessageUI
}