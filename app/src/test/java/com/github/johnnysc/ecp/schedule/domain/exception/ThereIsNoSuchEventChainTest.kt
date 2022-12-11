package com.github.johnnysc.ecp.schedule.domain.exception

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.ExceptionChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import org.junit.Assert.*
import org.junit.Test

internal class ThereIsNoSuchEventChainTest {

    private val chain = ThereIsNoSuchEventChain(
        ExceptionChain.DefaultExceptionChain()
    )

    private val mapper = DomainException.Mapper.Base(TestManageResource())

    @Test
    fun `test exception chain with standard exceptions from data layer of weather feature`() {
        val expected = MessageUI.AiError("There is no such event!")
        val input = ThereIsNoSuchEventException()
        val actual = chain.handle(input).map(mapper)
        assertEquals(expected, actual)
    }

    @Test
    fun `test exception chain with unknown exception`() {
        val expected = MessageUI.AiError("Unknown exception")
        val input = IndexOutOfBoundsException()
        val actual = chain.handle(input).map(mapper)
        assertEquals(expected, actual)
    }


    private class TestManageResource : ManageResources {
        override fun string(id: Int): String {
            return when (id) {
                R.string.unknown_exception -> "Unknown exception"
                R.string.there_is_no_event -> "There is no such event!"
                else -> "Something went wrong"
            }
        }
    }
}