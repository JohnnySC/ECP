package com.github.johnnysc.ecp.schedule.data

import com.github.johnnysc.ecp.schedule.domain.EventDomain

interface ScheduleRepository {

    suspend fun todayEvents(): List<EventCache>

    suspend fun allEvents(): List<EventCache>

    suspend fun removeEvent(event: EventCache)

    suspend fun addEvent(event: EventCache)
}