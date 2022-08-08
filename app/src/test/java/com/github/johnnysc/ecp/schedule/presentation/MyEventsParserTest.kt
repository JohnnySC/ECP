package com.github.johnnysc.ecp.schedule.presentation

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.schedule.presentation.commands.myevents.ParseMyEvents
import org.junit.Assert
import org.junit.Test

internal class MyEventsParserTest {

    @Test
    fun `success when correct`() {
        val testManageResource = TestManageResources("My events")
        val parser = ParseMyEvents(testManageResource)
        Assert.assertEquals(false, parser.map("my events").isEmpty())
    }

    @Test
    fun `failed when incorrect`() {
        val testManageResource = TestManageResources("My events")
        val parser = ParseMyEvents(testManageResource)
        Assert.assertEquals(true, parser.map("ym stneve").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }
}