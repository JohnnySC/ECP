package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert
import org.junit.Test

internal class MessagesViewModelTest {

    @Test
    fun whenMessageForFirstChain_shouldBeHandledByFirstChain() {
        val testChainFactory = TestChainFactory(TestChainOne())
        val communication = TestCommunication()
        val dispatchers = TestDispatchers()
        val viewModel = MessagesViewModel(dispatchers = dispatchers, communication = communication, viewModelChain = testChainFactory)
        viewModel.handleInput("for first one")
        Assert.assertEquals("first msg", communication.msg)
    }

    @Test
    fun whenMessageForSecondChain_shouldBeHandledByFirstChain() {
        val testChainFactory = TestChainFactory(TestChainOne())
        val communication = TestCommunication()
        val dispatchers = TestDispatchers()
        val viewModel = MessagesViewModel(dispatchers = dispatchers, communication = communication, viewModelChain = testChainFactory)
        viewModel.handleInput("for second one")
        Assert.assertEquals("second msg", communication.msg)
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
        override fun canHandle(message: String): Boolean = message == "for first one"

        override suspend fun handle(message: String): MessageUI = MessageUI.Ai("0", "first msg")
    }

    private class TestChainTwo : FeatureChain.Handle {
        override suspend fun handle(message: String): MessageUI = MessageUI.Ai("0", "second msg")
    }

    private class TestCommunication : MessagesCommunication.Mutable {

        var msg = ""

        override fun map(data: MessageUI) {
            msg = data.content()
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) = Unit
    }

    private class TestDispatchers : Dispatchers.Abstract(StandardTestDispatcher(), StandardTestDispatcher())
}