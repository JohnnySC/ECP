package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface DeleteEventUseCase {

    suspend fun deleteEvent(name: String, date: Long): MessageUI
}