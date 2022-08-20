package com.github.johnnysc.ecp.schedule.data

interface EventCacheDataSource {

    suspend fun eventsWithinRange(start: Long, end: Long): List<EventCache>

    suspend fun allEvents(): List<EventCache>

    suspend fun removeEvent(event: EventCache)

    suspend fun addEvent(event: EventCache)
}