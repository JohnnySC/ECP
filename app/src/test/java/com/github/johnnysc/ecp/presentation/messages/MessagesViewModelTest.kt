package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import com.github.johnnysc.coremvvm.core.Dispatchers
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Test


internal class MessagesViewModelTest {

    class TestChainFactory(private val chain: FeatureChain.CheckAndHandle) : FeatureChain.Handle {
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

    @Test
    fun whenMessageForFirstChain_shouldBeHandledByFirstChain() {
        val testChainFactory = MessagesViewModelTest.TestChainFactory(TestChainOne())
        val communication = MessagesCommunication.Base()
        val lifecycleOwner = mockkClass(LifecycleOwner::class)
        val dispatchers = Dispatchers.Base()
        val viewModel = MessagesViewModel(dispatchers, communication, testChainFactory)
        communication.observe(lifecycleOwner) {
            Assert.assertEquals(MessageUI.Ai("0", "first msg"), it[0])
        }
        viewModel.handleInput("for first one")
    }

    @Test
    fun whenMessageForSecondChain_shouldBeHandledByFirstChain() {
        val testChainFactory = MessagesViewModelTest.TestChainFactory(TestChainOne())
        val communication = MessagesCommunication.Base()
        val lifecycleOwner = mockkClass(LifecycleOwner::class)
        val viewModel = MessagesViewModel(communication, testChainFactory)
        communication.observe(lifecycleOwner) {
            Assert.assertEquals(MessageUI.Ai("0", "second msg"), it[0])
        }
        viewModel.handleInput("for second one")
    }
}