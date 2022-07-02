package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.core.Dispatchers

abstract class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle,
) : FeatureChain.Handle {
    private lateinit var nextFeatureChain: FeatureChain.Handle

    override suspend fun handle(message: String): MessageUI {
        var result: MessageUI
        if (featureChain.canHandle(message)) {
            result = featureChain.handle(message)
        } else {
            result = nextFeatureChain.handle(message)
        }
        return result
    }

    fun setNextFeatureChain(featureChain: FeatureChain.CheckAndHandle) {
        nextFeatureChain = featureChain
    }
}