package com.github.johnnysc.ecp.schedule.presentation

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.schedule.domain.ScheduleInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.AbstractFeatureChain
import com.github.johnnysc.ecp.schedule.presentation.commands.ConvertTime
import com.github.johnnysc.ecp.schedule.presentation.commands.addevent.ParseAddEvent
import com.github.johnnysc.ecp.schedule.presentation.commands.addevent.AddEventCommand
import com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent.ParseDeleteEvent
import com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent.DeleteEventCommand
import com.github.johnnysc.ecp.schedule.presentation.commands.myday.ParseMyDay
import com.github.johnnysc.ecp.schedule.presentation.commands.myday.MyDayCommand
import com.github.johnnysc.ecp.schedule.presentation.commands.myevents.ParseMyEvents
import com.github.johnnysc.ecp.schedule.presentation.commands.myevents.MyEventsCommand

class ScheduleChain(
    interactor: ScheduleInteractor,
    convertTime: ConvertTime,
    private val manageResources: ManageResources,
    commands: List<Command<ScheduleInteractor>> = listOf(
        MyEventsCommand(ParseMyEvents(manageResources)),
        MyDayCommand(ParseMyDay(manageResources)),
        AddEventCommand(ParseAddEvent(manageResources, convertTime)),
        DeleteEventCommand(ParseDeleteEvent(manageResources, convertTime))
    )
) : AbstractFeatureChain<ScheduleInteractor>(interactor, commands)