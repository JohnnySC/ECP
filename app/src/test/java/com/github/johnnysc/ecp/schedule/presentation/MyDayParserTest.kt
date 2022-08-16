package com.github.johnnysc.ecp.schedule.presentation

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.schedule.presentation.commands.myday.ParseMyDay
import org.junit.Assert
import org.junit.Test

internal class MyDayParserTest {

    @Test
    fun `success when correct`() {
        val testManageResource = TestManageResources("My day")
        val parser = ParseMyDay(testManageResource)
        Assert.assertEquals(false, parser.map("my day").isEmpty())
    }

    @Test
    fun `failed when incorrect`() {
        val testManageResource = TestManageResources("My day")
        val parser = ParseMyDay(testManageResource)
        Assert.assertEquals(true, parser.map("ym yad").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }
}