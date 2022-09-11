package com.github.johnnysc.ecp.schedule.data

interface EventCacheDataSource {

    suspend fun eventsWithinRange(start: Long, end: Long): List<EventCache>

    suspend fun allEvents(): List<EventCache> = eventsWithinRange(Long.MIN_VALUE, Long.MAX_VALUE)

    suspend fun removeEvent(event: EventCache)

    suspend fun addEvent(event: EventCache)
}