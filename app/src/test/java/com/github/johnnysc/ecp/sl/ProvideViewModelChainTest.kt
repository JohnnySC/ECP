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

        val viewModelChainBaseTestOne = ViewModelChainTest(featureChainOne)
        val viewModelChainBaseTestTwo = ViewModelChainTest(featureChainOne)
        val viewModelChainBaseTestThree = ViewModelChainTest(featureChainOne)

        val provideViewModelChainTestOne = ProvideViewModelChainTest(viewModelChainBaseTestOne)
        val provideViewModelChainTestTwo = ProvideViewModelChainTest(viewModelChainBaseTestTwo)
        val provideViewModelChainTestThree = ProvideViewModelChainTest(viewModelChainBaseTestThree)

        val result = ProvideViewModelChain.Base(
            provideViewModelChainTestOne,
            provideViewModelChainTestTwo,
            provideViewModelChainTestThree
        ).viewModelChain()
        Assert.assertEquals(viewModelChainBaseTestOne, result)

        var nextFeatureChain = (result as ViewModelChainTest).provideNextFeatureChain()
        Assert.assertEquals(viewModelChainBaseTestTwo, nextFeatureChain)

        nextFeatureChain = (nextFeatureChain as ViewModelChainTest).provideNextFeatureChain()
        Assert.assertEquals(viewModelChainBaseTestThree, nextFeatureChain)
    }

    private class ViewModelChainTest(featureChain: FeatureChain.CheckAndHandle<Boolean>) :
        ViewModelChain(featureChain) {

        fun provideNextFeatureChain(): FeatureChain.Check<MessageUI> {
            return nextFeatureChain
        }
    }

    private class ProvideViewModelChainTest(private val viewModelChain: ViewModelChainTest) :
        ProvideViewModelChain<ViewModelChainTest> {
        override fun viewModelChain() = viewModelChain
    }

    private class FeatureTestChain : FeatureChain.CheckAndHandle<Boolean> {

        override suspend fun canHandle(message: String): Boolean = true

        override suspend fun handle(): MessageUI = MessageUI.Empty()
    }


}