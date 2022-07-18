package com.github.johnnysc.ecp.presentation.messages

import org.junit.Assert
import org.junit.Test

internal class MessagesArrayListTest {

    @Test
    fun `test list`() {
        val list = MessagesArrayList()
        val messageUser = MessageUI.User("message user")
        val messageAi = MessageUI.Ai("message ai")

        list.add(messageUser)
        var expected = listOf<MessageUI>(MessageUI.User("message user", "0"))
        Assert.assertEquals(expected, list)

        list.add(messageAi)
        expected = listOf(
            MessageUI.User("message user", "0"),
            MessageUI.Ai("message ai", "1"),
        )
        Assert.assertEquals(expected, list)
    }
}