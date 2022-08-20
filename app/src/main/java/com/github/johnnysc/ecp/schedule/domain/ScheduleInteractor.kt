package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.ecp.domain.AbstractInteractor
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.ExceptionChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.schedule.data.EventCache

interface ScheduleInteractor : AddEventUseCase, DeleteEventUseCase, MyDayUseCase, MyEventsUseCase {

    class Base(
        private val repository: ScheduleRepository,
        private val eventDomainToTodayMessageUi: EventDomain.Mapper<MessageUI>,
        private val eventDomainToAllEventsMessageUi: EventDomain.Mapper<MessageUI>,
        private val eventDomainToAddMessageUi: EventDomain.Mapper<MessageUI>,
        private val eventDomainToDeleteMessageUi: EventDomain.Mapper<MessageUI>,
        private val cacheToStringMapper: EventCache.Mapper<String>,
        exceptionChain: ExceptionChain,
        domainExceptionToMessageUi: DomainException.Mapper<MessageUI>
    ) : AbstractInteractor(exceptionChain, domainExceptionToMessageUi), ScheduleInteractor {

        override suspend fun addEvent(name: String, date: Long): MessageUI = handle {
            repository.addEvent(EventCache.Base(name, date))
            eventDomainToAddMessageUi.map(emptyList())
        }

        override suspend fun deleteEvent(name: String, date: Long): MessageUI = handle {
            repository.removeEvent(EventCache.Base(name, date))
            eventDomainToDeleteMessageUi.map(emptyList())
        }

        override suspend fun myDay(): MessageUI = handle {
            eventDomainToTodayMessageUi.map(repository.todayEvents().map { it.map(cacheToStringMapper) })
        }

        override suspend fun myEvents(): MessageUI = handle {
            eventDomainToAllEventsMessageUi.map(repository.allEvents().map { it.map(cacheToStringMapper) })
        }
    }
}