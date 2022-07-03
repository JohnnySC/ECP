package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class MessagesViewModelTest {

    @Test
    fun whenMessageForFirstChain_shouldBeHandledByFirstChain() = runTest {
        val testChainFactory = TestChainFactory(TestChainOne())
        val communication = TestCommunication()
        val dispatchers = TestDispatchers()
        val viewModel = MessagesViewModel(dispatchers = dispatchers, communication = communication, viewModelChain = testChainFactory)
        viewModel.handleInput("For first one")
        Assert.assertEquals("For first one", communication.messages[0].content())
        Assert.assertEquals("First message", communication.messages[1].content())
    }

    @Test
    fun whenMessageForSecondChain_shouldBeHandledByFirstChain() = runTest {
        val testChainFactory = TestChainFactory(TestChainOne())
        val communication = TestCommunication()
        val dispatchers = TestDispatchers()
        val viewModel = MessagesViewModel(dispatchers = dispatchers, communication = communication, viewModelChain = testChainFactory)
        viewModel.handleInput("For second one")
        Assert.assertEquals("For second one", communication.messages[0].content())
        Assert.assertEquals("I don't understand you", communication.messages[1].content())
    }

    private class TestChainFactory(private val chain: FeatureChain.CheckAndHandle) : FeatureChain.Handle {
        private val nextChain = TestChainTwo()

        override suspend fun handle(message: String): MessageUI {
            return if (chain.canHandle(message)) {
                chain.handle(message)
            } else {
                nextChain.handle(message)
            }
        }
    }

    private class TestChainOne : FeatureChain.CheckAndHandle {
        override fun canHandle(message: String): Boolean = message == "For first one"

        override suspend fun handle(message: String): MessageUI = MessageUI.Ai("0", "First message")
    }

    private class TestChainTwo : FeatureChain.Handle {
        override suspend fun handle(message: String): MessageUI = MessageUI.AiError("0", "I don't understand you")
    }

    private class TestCommunication : MessagesCommunication.Mutable {

        val messages = mutableListOf<MessageUI>()

        override fun map(data: MessageUI) {
            messages.add(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) = Unit
    }

    private class TestDispatchers : Dispatchers.Abstract(StandardTestDispatcher(), StandardTestDispatcher())
}