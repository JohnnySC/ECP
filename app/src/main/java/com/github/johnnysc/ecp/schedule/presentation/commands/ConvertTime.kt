package com.github.johnnysc.ecp.schedule.presentation.commands

import java.text.SimpleDateFormat
import java.util.*

interface ConvertTime {

    fun fromTimeToString(time: Long): String

    fun fromStringToTime(string: String): Long

    class Base(
        private val pattern: String = "dd.MM.yyyy"
    ) : ConvertTime {

        override fun fromTimeToString(time: Long): String =
            SimpleDateFormat(pattern, Locale.getDefault()).format(Date(time))

        override fun fromStringToTime(string: String): Long =
            SimpleDateFormat(pattern, Locale.getDefault()).parse(string)?.time!!
    }
}