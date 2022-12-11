package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.ExceptionChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.schedule.domain.exception.ThereIsNoSuchEventChain
import com.github.johnnysc.ecp.schedule.domain.exception.ThereIsNoSuchEventException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class ScheduleInteractorTest {

    private val chain = ThereIsNoSuchEventChain(
        ExceptionChain.DefaultExceptionChain()
    )

    private val manageResources = TestManageResource()

    private val eventDomainScheduleToMessageUI = EventDomain.Mapper.BaseSchedule(manageResources)
    private val eventDomainTodayToMessageUI = EventDomain.Mapper.BaseToday(manageResources)
    private val eventDomainAddEventToMessageUI = EventDomain.Mapper.AddSuccess(manageResources)
    private val eventDomainDeleteEventToMessageUI =
        EventDomain.Mapper.DeleteSuccess(manageResources)

    private val domainExceptionToUIMapper = DomainException.Mapper.Base(manageResources)

    private val mockEvents = listOf("29.08.2022: Event 1", "29.08.2022: Event 2")

    @Test
    fun `success add event`() = runBlocking {
        val repository = TestScheduleRepository()
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.Ai(manageResources.string(R.string.task_successfully_added))
        val actual = interactor.addEvent("Some event", 0L)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `success delete event`() = runBlocking {
        val repository = TestScheduleRepository()
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.Ai(manageResources.string(R.string.task_successfully_delete))
        val actual = interactor.deleteEvent("Some event", 0L)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `failed delete event`() = runBlocking {
        val repository = TestScheduleRepository(isNonExistentEvent = true)
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.AiError(manageResources.string(R.string.there_is_no_event))
        val actual = interactor.deleteEvent("Some event", 0L)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `success existent daily events`() = runBlocking {
        val repository = TestScheduleRepository(mockEvents)
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.Ai(
            "${manageResources.string(R.string.your_events_today)}\n" +
                    "29.08.2022: Event 1\n" +
                    "29.08.2022: Event 2"
        )
        val actual = interactor.myDay()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `success empty daily events`() = runBlocking {
        val repository = TestScheduleRepository()
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.Ai(manageResources.string(R.string.there_are_no_events))
        val actual = interactor.myDay()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `success existent all events`() = runBlocking {
        val repository = TestScheduleRepository(mockEvents)
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.Ai(
            "${manageResources.string(R.string.your_events)}\n" +
                    "29.08.2022: Event 1\n" +
                    "29.08.2022: Event 2"
        )
        val actual = interactor.myEvents()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `success empty all events`() = runBlocking {
        val repository = TestScheduleRepository()
        val interactor = ScheduleInteractor.Base(
            repository,
            eventDomainTodayToMessageUI,
            eventDomainScheduleToMessageUI,
            eventDomainAddEventToMessageUI,
            eventDomainDeleteEventToMessageUI,
            chain,
            domainExceptionToUIMapper
        )
        val expected = MessageUI.Ai(manageResources.string(R.string.there_are_no_events))
        val actual = interactor.myEvents()
        Assert.assertEquals(expected, actual)
    }

    private class TestScheduleRepository(
        private val events: List<String> = emptyList(),
        private val isNonExistentEvent: Boolean = false
    ) : ScheduleRepository {

        override suspend fun todayEvents(): List<String> = events

        override suspend fun allEvents(): List<String> = events

        override suspend fun removeEvent(name: String, date: Long) {
            if (isNonExistentEvent)
                throw ThereIsNoSuchEventException()
        }

        override suspend fun addEvent(name: String, date: Long) {}
    }

    private class TestManageResource : ManageResources {
        override fun string(id: Int): String {
            return when (id) {
                R.string.unknown_exception -> "Unknown exception"
                R.string.task_successfully_added -> "Task successfully added!"
                R.string.task_successfully_delete -> "Task successfully delete!"
                R.string.your_events_today -> "What you\'re doing today:"
                R.string.there_are_no_events -> "Unfortunately, there are no events!"
                R.string.your_events -> "What you do:"
                R.string.there_is_no_event -> "There is no such event!"
                else -> "Something went wrong"
            }
        }
    }
}