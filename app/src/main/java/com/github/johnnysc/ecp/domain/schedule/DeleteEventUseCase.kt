package com.github.johnnysc.ecp.domain.schedule

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface DeleteEventUseCase {

    suspend fun deleteEvent(name: String, date: String): MessageUI
}