package com.github.johnnysc.ecp.schedule.presentation.commands

import java.text.SimpleDateFormat
import java.util.*

interface ConvertTime {

    fun fromTimeToString(time: Long): String

    fun fromStringToTime(string: String): Long

    class Base : ConvertTime {

        override fun fromTimeToString(time: Long): String =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(time))

        override fun fromStringToTime(string: String): Long =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(string)?.time!!
    }
}