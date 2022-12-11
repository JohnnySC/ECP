package com.github.johnnysc.ecp.schedule.domain

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import org.junit.Assert
import org.junit.Test

class EventDomainMapperTest {

    @Test
    fun `add message that must return same in any case`() {
        val testManageResource = TestManageResource("Some output")
        val mapper = EventDomain.Mapper.AddSuccess(testManageResource)
        val expectedMessage = MessageUI.Ai("Some output")
        Assert.assertEquals(expectedMessage, mapper.map(emptyList()))
        Assert.assertEquals(
            expectedMessage,
            mapper.map(listOf("1", "2", "some string"))
        )
    }

    @Test
    fun `delete message that must return same in any case`() {
        val testManageResource = TestManageResource("Some output")
        val mapper = EventDomain.Mapper.DeleteSuccess(testManageResource)
        val expectedMessage = MessageUI.Ai("Some output")
        Assert.assertEquals(expectedMessage, mapper.map(emptyList()))
        Assert.assertEquals(
            expectedMessage,
            mapper.map(listOf("1", "2", "some string"))
        )
    }

    @Test
    fun `today scheduled that print all tasks in list-like message`() {
        val testManageResource = TestManageResource("Some output")
        val mapper = EventDomain.Mapper.BaseToday(testManageResource)
        val expectedEmptyMessage = MessageUI.Ai("Some output")
        val expectedMessage = MessageUI.Ai("Some output\n1\n2\n3")
        Assert.assertEquals(expectedEmptyMessage, mapper.map(emptyList()))
        Assert.assertEquals(
            expectedMessage,
            mapper.map(listOf("1", "2", "3"))
        )
    }

    @Test
    fun `scheduled that print all tasks in list-like message`() {
        val testManageResource = TestManageResource("Some output")
        val mapper = EventDomain.Mapper.BaseSchedule(testManageResource)
        val expectedEmptyMessage = MessageUI.Ai("Some output")
        val expectedMessage = MessageUI.Ai("Some output\n1\n2\n3")
        Assert.assertEquals(expectedEmptyMessage, mapper.map(emptyList()))
        Assert.assertEquals(
            expectedMessage,
            mapper.map(listOf("1", "2", "3"))
        )
    }

    private class TestManageResource(private val output: String) : ManageResources {

        override fun string(id: Int): String = output
    }
}