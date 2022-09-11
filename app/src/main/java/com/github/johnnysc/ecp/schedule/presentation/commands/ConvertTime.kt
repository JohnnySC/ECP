package com.github.johnnysc.ecp.schedule.presentation.commands

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

interface ConvertTime {

    fun fromTimeToString(time: Long): String

    fun fromStringToTime(string: String): Long

    class Base(
        private val dateFormat: SimpleDateFormat =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    ) : ConvertTime {

        override fun fromTimeToString(time: Long): String =
            dateFormat.format(time)

        override fun fromStringToTime(string: String): Long =
            dateFormat.parse(string)?.time!!
    }
}