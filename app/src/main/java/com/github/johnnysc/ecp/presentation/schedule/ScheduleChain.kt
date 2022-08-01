package com.github.johnnysc.ecp.presentation.schedule

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.schedule.ScheduleInteractor
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.schedule.commands.addevent.ParseAddEvent
import com.github.johnnysc.ecp.presentation.schedule.commands.addevent.ScheduleAddEventCommand
import com.github.johnnysc.ecp.presentation.schedule.commands.deleteevent.ParseDeleteEvent
import com.github.johnnysc.ecp.presentation.schedule.commands.deleteevent.ScheduleDeleteEventCommand
import com.github.johnnysc.ecp.presentation.schedule.commands.myday.ParseMyDay
import com.github.johnnysc.ecp.presentation.schedule.commands.myday.ScheduleMyDayCommand
import com.github.johnnysc.ecp.presentation.schedule.commands.myschedule.ParseMySchedule
import com.github.johnnysc.ecp.presentation.schedule.commands.myschedule.ScheduleMyScheduleCommand

class ScheduleChain(
    private val interactor: ScheduleInteractor,
    private val manageResources: ManageResources,
    private val commands: List<Command<ScheduleInteractor>> = listOf(
        ScheduleMyScheduleCommand(ParseMySchedule(manageResources)),
        ScheduleMyDayCommand(ParseMyDay(manageResources)),
        ScheduleAddEventCommand(ParseAddEvent(manageResources)),
        ScheduleDeleteEventCommand(ParseDeleteEvent(manageResources))
    )
) : FeatureChain.CheckAndHandle {

    private var currentCommand: Command<ScheduleInteractor> = Command.Empty()

    override fun canHandle(message: String): Boolean {
        val find = commands.find {
            it.canHandle(message)
        }
        find?.let {
            currentCommand = it
        }
        return find != null
    }

    override suspend fun handle(message: String): MessageUI {
        val result = currentCommand.handle(interactor)
        currentCommand = Command.Empty()
        return result
    }
}