package com.github.johnnysc.ecp.schedule.presentation

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent.DeleteEvent
import com.github.johnnysc.ecp.schedule.presentation.commands.deleteevent.ParseDeleteEvent
import org.junit.Assert
import org.junit.Test

internal class DeleteEventParserTest {

    @Test
    fun `success when correct request`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource)
        Assert.assertEquals(DeleteEvent("homework", "today"), parser.map("Delete an event homework on today"))
    }

    @Test
    fun `failed when no date`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource)
        Assert.assertEquals(true, parser.map("Delete an event homework on").isEmpty())
    }

    @Test
    fun `failed when nothing`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource)
        Assert.assertEquals(true, parser.map("Delete an event ").isEmpty())
    }

    @Test
    fun `failed when no name and date`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource)
        Assert.assertEquals(true, parser.map("Delete an event on ").isEmpty())
    }

    @Test
    fun `failed when incorrect message`() {
        val testManageResource = TestManageResources("Delete an event")
        val parser = ParseDeleteEvent(testManageResource)
        Assert.assertEquals(true, parser.map("eteled na tneve").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }
}