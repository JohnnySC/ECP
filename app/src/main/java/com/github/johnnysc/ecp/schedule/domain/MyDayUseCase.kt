package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface MyDayUseCase {

    suspend fun myDay(): MessageUI
}