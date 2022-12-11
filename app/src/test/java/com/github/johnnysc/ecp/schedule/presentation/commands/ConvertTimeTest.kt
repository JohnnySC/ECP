package com.github.johnnysc.ecp.schedule.presentation.commands

import org.junit.Assert
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

internal class ConvertTimeTest {

    @Test
    fun `success from long to string format with`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("Europe/Moscow")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        val dateEpochMillis = 1662843600000
        val expectedDateHuman = "11.09.2022"
        Assert.assertEquals(
            expectedDateHuman,
            convertTime.fromTimeToString(dateEpochMillis)
        )
    }

    @Test
    fun `success from same long but to different string due to timezone`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("America/Denver")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        val dateEpochMillis = 1662843600000
        val expectedDateHuman = "10.09.2022"
        Assert.assertEquals(
            expectedDateHuman,
            convertTime.fromTimeToString(dateEpochMillis)
        )
    }

    @Test
    fun `success from string to long format with`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("Europe/Moscow")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        val expectedDateEpochMillis = 1662843600000
        val dateHuman = "11.09.2022"
        Assert.assertEquals(
            expectedDateEpochMillis,
            convertTime.fromStringToTime(dateHuman)
        )
    }

    @Test
    fun `success from same string but to different long due to timezone`() {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("America/Denver")
        val convertTime = ConvertTime.Base(simpleDateFormat)
        val expectedDateEpochMillis = 1662876000000
        val dateHuman = "11.09.2022"
        Assert.assertEquals(
            expectedDateEpochMillis,
            convertTime.fromStringToTime(dateHuman)
        )
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