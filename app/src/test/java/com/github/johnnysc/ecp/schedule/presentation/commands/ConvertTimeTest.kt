package com.github.johnnysc.ecp.schedule.presentation.commands

import org.junit.Assert
import org.junit.Test

internal class ConvertTimeTest {

    @Test
    fun `success from long to string format with proper long`() {
        val convertTime = ConvertTime.Base()
        Assert.assertEquals("11.09.2022", convertTime.fromTimeToString(	1662888445000))
    }

    @Test
    fun `success from long to string format with proper long`() {
        val convertTime = ConvertTime.Base()
        Assert.assertEquals("11.09.2022", convertTime.fromTimeToString(	1662888445000))
    }
}