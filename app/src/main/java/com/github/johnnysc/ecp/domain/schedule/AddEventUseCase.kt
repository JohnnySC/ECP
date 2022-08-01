package com.github.johnnysc.ecp.domain.schedule

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface AddEventUseCase {

    suspend fun addEvent(name: String, date: String): MessageUI
}