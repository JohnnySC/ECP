package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface EventDomain {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(private val events: List<String>) : EventDomain {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(events)
    }

    interface Mapper<T> {

        fun map(events: List<String>): T

        class AddSuccess(private val manageResources: ManageResources) : Mapper<MessageUI> {

            override fun map(events: List<String>): MessageUI =
                MessageUI.Ai(manageResources.string(R.string.task_successfully_added))
        }

        class DeleteSuccess(private val manageResources: ManageResources) : Mapper<MessageUI> {

            override fun map(events: List<String>): MessageUI =
                MessageUI.Ai(manageResources.string(R.string.task_successfully_delete))
        }

        class BaseToday(private val manageResources: ManageResources) : Mapper<MessageUI> {

            override fun map(events: List<String>): MessageUI {
                if (events.isEmpty())
                    return MessageUI.Ai(manageResources.string(R.string.there_are_no_events))
                val list = buildString {
                    for (i in events.indices) {
                        append(events[i])
                        if (i != events.size - 1)
                            append("\n")
                    }
                }
                return MessageUI.Ai("${manageResources.string(R.string.your_events_today)}$list")
            }
        }

        class BaseSchedule(private val manageResources: ManageResources) : Mapper<MessageUI> {

            override fun map(events: List<String>): MessageUI {
                if (events.isEmpty())
                    return MessageUI.Ai(manageResources.string(R.string.there_are_no_events))
                val list = buildString {
                    for (i in events.indices) {
                        append(events[i])
                        if (i != events.size - 1)
                            append("\n")
                    }
                }
                return MessageUI.Ai("${manageResources.string(R.string.your_events)}$list")
            }
        }
    }
}