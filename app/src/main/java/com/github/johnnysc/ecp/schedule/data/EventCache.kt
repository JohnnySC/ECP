package com.github.johnnysc.ecp.schedule.data

import com.github.johnnysc.ecp.schedule.presentation.commands.ConvertTime
import java.text.SimpleDateFormat
import java.util.*

interface EventCache {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val name: String,
        private val date: Long
    ) : EventCache {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(name, date)
    }

    interface Mapper<out T> {

        fun map(name: String, date: Long): T

        class ToString(
            private val convertTime: ConvertTime
        ) : Mapper<String> {

            override fun map(name: String, date: Long): String =
                "${convertTime.fromTimeToString(date)}: $name"
        }
    }
}