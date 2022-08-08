package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface MyEventsUseCase {

    suspend fun myEvents(): MessageUI
}