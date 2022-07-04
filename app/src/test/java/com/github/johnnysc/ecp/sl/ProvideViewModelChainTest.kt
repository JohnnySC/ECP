package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain
import org.junit.Assert
import org.junit.Test

class ProvideViewModelChainTest {

    @Test
    fun `test creation of ViewModelChain creation`() {
        val featureChainOne = FeatureTestChain()

        val viewModelChainBaseTestOne = ViewModelChainBaseTest(featureChainOne)
        val viewModelChainBaseTestTwo = ViewModelChainBaseTest(featureChainOne)
        val viewModelChainBaseTestThree = ViewModelChainBaseTest(featureChainOne)

        val provideViewModelChainTestOne = ProvideViewModelChainTest(viewModelChainBaseTestOne)
        val provideViewModelChainTestTwo = ProvideViewModelChainTest(viewModelChainBaseTestTwo)
        val provideViewModelChainTestThree = ProvideViewModelChainTest(viewModelChainBaseTestThree)

        val result = ProvideViewModelChain.Base(
            provideViewModelChainTestOne,
            provideViewModelChainTestTwo,
            provideViewModelChainTestThree
        ).viewModelChain() as ViewModelChainTest
        Assert.assertEquals(viewModelChainBaseTestOne, result)

        var nextFeatureChain = result.provideNextFeatureChain()
        Assert.assertEquals(viewModelChainBaseTestTwo, nextFeatureChain)

        nextFeatureChain = (nextFeatureChain as ViewModelChainTest).provideNextFeatureChain()
        Assert.assertEquals(viewModelChainBaseTestThree, nextFeatureChain)
    }

    private open class ViewModelChainTest(featureChain: FeatureChain.CheckAndHandle) :
        ViewModelChain(featureChain) {
        fun provideNextFeatureChain(): FeatureChain.Handle {
            return nextFeatureChain
        }
    }

    private class ProvideViewModelChainTest(private val viewModelChain: ViewModelChainTest) :
        ProvideViewModelChain<ViewModelChainTest> {
        override fun viewModelChain() = viewModelChain
    }


    private class ViewModelChainBaseTest(nextFeatureChain: FeatureChain.CheckAndHandle) :
        ViewModelChainTest(nextFeatureChain)


    private class FeatureTestChain : FeatureChain.CheckAndHandle {
        override fun canHandle(message: String): Boolean {
            return true
        }

        override suspend fun handle(message: String): MessageUI {
            return MessageUI.Empty()
        }
    }


}