package com.github.johnnysc.ecp.schedule.presentation

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.schedule.presentation.commands.ConvertTime
import com.github.johnnysc.ecp.schedule.presentation.commands.addevent.AddEvent
import com.github.johnnysc.ecp.schedule.presentation.commands.addevent.ParseAddEvent
import org.junit.Assert
import org.junit.Test

internal class AddEventParserTest {

    @Test
    fun `success when correct request`() {
        val testManageResource = TestManageResources("Add an event")
        val parser = ParseAddEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(
            AddEvent("homework", 1234L),
            parser.map("add an event homework on 20.08.2022")
        )
    }

    @Test
    fun `failed when no date`() {
        val testManageResource = TestManageResources("Add an event")
        val parser = ParseAddEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("add an event homework on").isEmpty())
    }

    @Test
    fun `failed when nothing`() {
        val testManageResource = TestManageResources("Add an event")
        val parser = ParseAddEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("add an event ").isEmpty())
    }

    @Test
    fun `failed when no name and date`() {
        val testManageResource = TestManageResources("Add an event")
        val parser = ParseAddEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("add an event on ").isEmpty())
    }

    @Test
    fun `failed when incorrect message`() {
        val testManageResource = TestManageResources("Add an event")
        val parser = ParseAddEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("dda na tneve").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }

    private class TestConvertTime : ConvertTime {

        override fun fromTimeToString(time: Long): String = "nice time"

        override fun fromStringToTime(string: String): Long = 1234L
    }
}