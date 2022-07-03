package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class MessagesViewModelTest {

    @Test
    fun whenMessageForFirstChain_shouldBeHandledByFirstChain() = runTest {
        val testChainFactory = TestChainFactory(TestChainOne())
        testChainFactory.setNextFeatureChain(TestChainTwo())
        val communication = TestCommunication()
        val testDispatcher = StandardTestDispatcher()
        val dispatchers = TestDispatchers(testDispatcher, testDispatcher)
        val viewModel = MessagesViewModel(
            dispatchers = dispatchers,
            communication = communication,
            viewModelChain = testChainFactory
        )
        viewModel.handleInput("For first one")
        Assert.assertEquals(MessageUI.User("0", "For first one"), communication.messages[0])
        Assert.assertEquals(MessageUI.User("1", "First message"), communication.messages[1])
    }

    @Test
    fun whenMessageForSecondChain_shouldBeHandledByFirstChain() = runTest {
        val testChainFactory = TestChainFactory(TestChainOne())
        testChainFactory.setNextFeatureChain(TestChainTwo())
        val communication = TestCommunication()
        val testDispatcher = StandardTestDispatcher()
        val dispatchers = TestDispatchers(testDispatcher, testDispatcher)
        val viewModel = MessagesViewModel(
            dispatchers = dispatchers,
            communication = communication,
            viewModelChain = testChainFactory
        )
        viewModel.handleInput("For second one")
        Assert.assertEquals(MessageUI.User("0", "For second one"), communication.messages[0])
        Assert.assertEquals(MessageUI.User("1", "I don't understand you"), communication.messages[1])
    }

    private class TestChainFactory(feature: FeatureChain.CheckAndHandle) : ViewModelChain(feature)

    private class TestChainOne : FeatureChain.CheckAndHandle {
        override fun canHandle(message: String): Boolean = message == "For first one"

        override suspend fun handle(message: String): MessageUI = MessageUI.Ai("1", "First message")
    }

    private class TestChainTwo : FeatureChain.Handle {
        override suspend fun handle(message: String): MessageUI = MessageUI.AiError("1", "I don't understand you")
    }

    private class TestCommunication : MessagesCommunication.Mutable {

        val messages = mutableListOf<MessageUI>()

        override fun map(data: MessageUI) {
            messages.add(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) = Unit
    }

    private class TestDispatchers(ui: CoroutineDispatcher, background: CoroutineDispatcher) :
        Dispatchers.Abstract(ui, background)
}