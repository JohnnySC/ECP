package com.github.johnnysc.ecp.presentation.messages

import org.junit.Assert
import org.junit.Test

internal class MessagesCommunicationMapperTest {

    @Test
    fun `test map`() {
        val mapper = MessagesCommunication.Mapper.Base()
        val messageUser = MessageUI.User("message")

        val actual = mapper.map(messageUser, null)
        val expected = listOf(MessageUI.User("message", "0"))
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test map twice`() {
        val mapper = MessagesCommunication.Mapper.Base()
        val messageUser = MessageUI.User("message user")
        val messageAi = MessageUI.Ai("message ai")

        var actual = mapper.map(messageUser, emptyList())
        actual = mapper.map(messageAi, actual)
        val expected = listOf(
            MessageUI.User("message user", "0"),
            MessageUI.Ai("message ai", "1")
        )
        Assert.assertEquals(expected, actual)
    }
}