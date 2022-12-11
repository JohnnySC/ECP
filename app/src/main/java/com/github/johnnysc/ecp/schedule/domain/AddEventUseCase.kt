package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface AddEventUseCase {

    suspend fun addEvent(name: String, date: Long): MessageUI
}