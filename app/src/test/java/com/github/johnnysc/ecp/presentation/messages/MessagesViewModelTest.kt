package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Test

internal class MessagesViewModelTest {

    @Test
    fun `test successful response`() = runBlocking {
        val testChainFactory = TestChainFactory(TestChainOne())
        testChainFactory.setNextFeatureChain(TestChainTwo())
        val communication = TestCommunication()
        val dispatchers = TestDispatchers()
        val viewModel = MessagesViewModel(
            dispatchers = dispatchers,
            communication = communication,
            viewModelChain = testChainFactory
        )
        viewModel.handleInput("For first one")
        Assert.assertEquals(MessageUI.User("For first one", "0"), communication.messages[0])
        Assert.assertEquals(MessageUI.Ai("First message", "1"), communication.messages[1])
    }

    @Test
    fun `test error response`() = runBlocking {
        val testChainFactory = TestChainFactory(TestChainOne())
        testChainFactory.setNextFeatureChain(TestChainTwo())
        val communication = TestCommunication()
        val dispatchers = TestDispatchers()
        val viewModel = MessagesViewModel(
            dispatchers = dispatchers,
            communication = communication,
            viewModelChain = testChainFactory
        )
        viewModel.handleInput("For second one")
        Assert.assertEquals(MessageUI.User("For second one", "0"), communication.messages[0])
        Assert.assertEquals(MessageUI.AiError("I don't understand you", "1"), communication.messages[1])
    }

    private class TestChainFactory(feature: FeatureChain.CheckAndHandle) : ViewModelChain(feature)

    private class TestChainOne : FeatureChain.CheckAndHandle {
        override fun canHandle(message: String): Boolean = message == "For first one"

        override suspend fun handle(message: String): MessageUI = MessageUI.Ai("First message")
    }

    private class TestChainTwo : FeatureChain.Handle {
        override suspend fun handle(message: String): MessageUI = MessageUI.AiError("I don't understand you")
    }

    private class TestCommunication(
        private val mapper: MessagesCommunication.Mapper = TestCommunicationMapper()
    ) : MessagesCommunication.Mutable {

        var messages = emptyList<MessageUI>()

        override fun map(data: MessageUI) {
            messages = mapper.map(data, messages)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) = Unit
    }

    private class TestCommunicationMapper : MessagesCommunication.Mapper {

        override fun map(messageUI: MessageUI, list: List<MessageUI>): List<MessageUI> {
            val result = list.toMutableList()
            val id = result.size.toString()
            result.add(messageUI.copyWithId(id))
            return result
        }
    }

    private class TestDispatchers(
        dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : Dispatchers.Abstract(dispatcher, dispatcher)
}