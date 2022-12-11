package com.github.johnnysc.ecp.schedule.domain


interface ScheduleRepository {

    suspend fun todayEvents(): List<String>

    suspend fun allEvents(): List<String>

    suspend fun removeEvent(name: String, date: Long)

    suspend fun addEvent(name: String, date: Long)
}