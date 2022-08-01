package com.github.johnnysc.ecp.domain.schedule

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface MyDayUseCase {

    suspend fun myDay(): MessageUI
}