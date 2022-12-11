package com.github.johnnysc.ecp.schedule.presentation

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.schedule.presentation.commands.ConvertTime
import com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent.DeleteEvent
import com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent.ParseDeleteEvent
import org.junit.Assert
import org.junit.Test

internal class DeleteEventParserTest {

    @Test
    fun `success when correct request`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(
            DeleteEvent("homework", 1234L),
            parser.map("Delete an event homework on 20.08.2022")
        )
    }

    @Test
    fun `failed when no date`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("Delete an event homework on").isEmpty())
    }

    @Test
    fun `failed when nothing`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("Delete an event ").isEmpty())
    }

    @Test
    fun `failed when no name and date`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("Delete an event on ").isEmpty())
    }

    @Test
    fun `failed when incorrect message`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource, TestConvertTime())
        Assert.assertEquals(true, parser.map("eteled na tneve").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }

    private class TestConvertTime : ConvertTime {

        override fun fromTimeToString(time: Long): String = "nice time"

        override fun fromStringToTime(string: String): Long = 1234L
    }
}