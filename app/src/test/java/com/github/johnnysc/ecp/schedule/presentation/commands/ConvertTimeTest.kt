package com.github.johnnysc.ecp.schedule.presentation.commands

import org.junit.Assert
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal class ConvertTimeTest {

    @Test
    fun `success from long to string format with`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("Europe/Moscow")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        Assert.assertEquals("11.09.2022", convertTime.fromTimeToString(1662843600000))
    }

    @Test
    fun `success from same long but to different string due to timezone`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("America/Denver")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        Assert.assertEquals("10.09.2022", convertTime.fromTimeToString(1662843600000))
    }

    @Test
    fun `success from string to long format with`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("Europe/Moscow")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        Assert.assertEquals(1662843600000, convertTime.fromStringToTime("11.09.2022"))
    }

    @Test
    fun `success from same string but to different long due to timezone`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("America/Denver")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        Assert.assertEquals(1662876000000, convertTime.fromStringToTime("11.09.2022"))
    }

    @Test
    fun `failed when unreadable string`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("America/Denver")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        Assert.assertThrows(ParseException::class.java) {
            convertTime.fromStringToTime("some non-data string")
        }
    }
}