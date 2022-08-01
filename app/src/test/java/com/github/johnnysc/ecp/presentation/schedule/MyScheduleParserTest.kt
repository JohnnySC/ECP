package com.github.johnnysc.ecp.presentation.schedule

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.schedule.commands.myschedule.ParseMySchedule
import org.junit.Assert
import org.junit.Test

internal class MyScheduleParserTest {

    @Test
    fun `success when correct`() {
        val testManageResource = TestManageResources("My schedule")
        val parser = ParseMySchedule(testManageResource)
        Assert.assertEquals(false, parser.map("my schedule").isEmpty())
    }

    @Test
    fun `failed when incorrect`() {
        val testManageResource = TestManageResources("My schedule")
        val parser = ParseMySchedule(testManageResource)
        Assert.assertEquals(true, parser.map("ym eludehcs").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }
}