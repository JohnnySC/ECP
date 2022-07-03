package com.github.johnnysc.ecp.sl.message

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.FeatureChain.UnknownMessageChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.messages.MessagesCommunication
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain
import com.github.johnnysc.ecp.sl.ProvideViewModelChain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.*
import org.junit.Test

class MessagesModuleTest {

    @Test
    fun `provide list of provides view models chain and check values in test message communication`() = runBlocking {
        val input = listOf("For first one", "For second one", "For third one", "some string")
        val defaultMessageId = "1"
        val expectedOne = listOf(
            MessageUI.User(defaultMessageId, input[0]),
            MessageUI.Ai(defaultMessageId, "First message")
        )
        var middleExpected = expectedOne.toMutableList()
        middleExpected.addAll(
            listOf(
                MessageUI.User(defaultMessageId, input[1]),
                MessageUI.Ai(defaultMessageId, "Second message")
            )
        )
        val expectedTwo = middleExpected.toList()
        middleExpected = expectedTwo.toMutableList()
        middleExpected.addAll(
            listOf(
                MessageUI.User(defaultMessageId, input[2]),
                MessageUI.Ai(defaultMessageId, "Third message")
            )
        )
        val expectedThree = middleExpected.toList()
        middleExpected = expectedThree.toMutableList()
        middleExpected.addAll(
            listOf(
                MessageUI.User(defaultMessageId, input[3]),
                MessageUI.AiError(defaultMessageId, "я вас не понимаю")
            )
        )
        val expectedFour = middleExpected.toList()

        val dispatchers = TestDispatchers(StandardTestDispatcher())
        val listOfProvideViewModelChains = listOf(
            ProvideViewModelChainOne(),
            ProvideViewModelChainTwo(),
            ProvideViewModelChainThree(),
            ProvideViewModelChainFour()
        )
        val testCommunication = TestCommunication()
        val viewModel = MessagesModule(
            providesViewModelChain = listOfProvideViewModelChains,
            messagesCommunication = testCommunication,
            dispatchers = dispatchers
        ).viewModel()

        viewModel.handleInput(input[0])
        assertEquals(expectedOne, testCommunication.messages)

        viewModel.handleInput(input[1])
        assertEquals(expectedTwo, testCommunication.messages)

        viewModel.handleInput(input[2])
        assertEquals(expectedThree, testCommunication.messages)

        viewModel.handleInpu(input[3])
        assertEquals(expectedFour, testCommunication.messages)

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
    private class ViewModelChainError : ViewModelChain(UnknownMessageChain(TestManageResources()))

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

    private class TestCommunication : MessagesCommunication.Mutable {

        val messages = mutableListOf<MessageUI>()

        override fun map(data: MessageUI) {
            messages.add(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) = Unit
    }

    private class TestDispatchers(dispatcher: CoroutineDispatcher) :
        Dispatchers.Abstract(dispatcher, dispatcher)

    private class TestManageResources : ManageResources {
        override fun string(id: Int): String {
            return "я вас не понимаю"
        }
    }


}