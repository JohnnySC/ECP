package com.github.johnnysc.ecp.sl

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProvideViewModelChainTest {

    @Test
    fun viewModelChain() = runBlocking {
        val input = listOf("For first one", "For second one", "For third one", "some string")
        val defaultMessageId = "1"
        val errorMessageId = "-1"
        val expectedOne = MessageUI.Ai(defaultMessageId, "First message")
        val expectedTwo = MessageUI.Ai(defaultMessageId, "Second message")
        val expectedThree = MessageUI.Ai(defaultMessageId, "Third message")
        val expectedFour = MessageUI.AiError(errorMessageId, "я вас не понимаю")

        val provideViewModelChain = ProvideViewModelChain.Base(
            ProvideViewModelChainOne(),
            ProvideViewModelChainTwo(),
            ProvideViewModelChainThree(),
            ProvideViewModelChainFour()
        )
        val viewModelChain = provideViewModelChain.viewModelChain()

        val actualOne = viewModelChain.handle(input[0])
        val actualTwo = viewModelChain.handle(input[1])
        val actualThree = viewModelChain.handle(input[2])
        val actualFour = viewModelChain.handle(input[3])

        assertEquals(expectedOne, actualOne)
        assertEquals(expectedTwo, actualTwo)
        assertEquals(expectedThree, actualThree)
        assertEquals(expectedFour, actualFour)


    }

    private class ProvideViewModelChainOne : ProvideViewModelChain<ViewModelChainOne> {

        override fun viewModelChain() = ViewModelChainOne()
    }

    private class ProvideViewModelChainTwo : ProvideViewModelChain<ViewModelChainTwo> {

        override fun viewModelChain() = ViewModelChainTwo()
    }

    private class ProvideViewModelChainThree : ProvideViewModelChain<ViewModelChainThree> {

        override fun viewModelChain() = ViewModelChainThree()
    }

    private class ProvideViewModelChainFour : ProvideViewModelChain<ViewModelChainError> {

        override fun viewModelChain() = ViewModelChainError()
    }

    private class ViewModelChainOne : ViewModelChain(TestChainOne())
    private class ViewModelChainTwo : ViewModelChain(TestChainTwo())
    private class ViewModelChainThree : ViewModelChain(TestChainThree())
    private class ViewModelChainError : ViewModelChain(
        FeatureChain.UnknownMessageChain(
            TestManageResources()
        )
    )

    private class TestChainOne : FeatureChain.CheckAndHandle {

        override fun canHandle(message: String) = message == "For first one"

        override suspend fun handle(message: String) = MessageUI.Ai("1", "First message")
    }

    private class TestChainTwo : FeatureChain.CheckAndHandle {

        override fun canHandle(message: String) = message == "For second one"

        override suspend fun handle(message: String) = MessageUI.Ai("1", "Second message")
    }

    private class TestChainThree : FeatureChain.CheckAndHandle {

        override fun canHandle(message: String) = message == "For third one"

        override suspend fun handle(message: String) = MessageUI.Ai("1", "Third message")
    }

    private class TestManageResources : ManageResources {
        override fun string(id: Int): String {
            return "я вас не понимаю"
        }
    }

}