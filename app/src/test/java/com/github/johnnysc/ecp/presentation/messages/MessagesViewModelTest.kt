package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.MutableLiveData
import com.github.johnnysc.coremvvm.core.Dispatchers
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
        val testChainFactory = TestChainFactory(TestChainOne())
        val liveData = MutableLiveData<List<MessageUI>>()
        val communication = MessagesCommunication.Base(liveData)
        val dispatchers = Dispatchers.Base()
        val viewModel = MessagesViewModel(dispatchers, communication, testChainFactory)
        viewModel.handleInput("for first one")
        Assert.assertEquals(MessageUI.Ai("0", "first msg"), liveData.value)
    }

    @Test
    fun whenMessageForSecondChain_shouldBeHandledByFirstChain() {
        val testChainFactory = TestChainFactory(TestChainOne())
        val liveData = MutableLiveData<List<MessageUI>>()
        val communication = MessagesCommunication.Base(liveData)
        val dispatchers = Dispatchers.Base()
        val viewModel = MessagesViewModel(dispatchers, communication, testChainFactory)
        viewModel.handleInput("for second one")
        Assert.assertEquals(MessageUI.Ai("0", "second msg"), liveData.value)
    }
}