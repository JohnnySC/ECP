package com.github.johnnysc.ecp.presentation.messages

abstract class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle,
) : FeatureChain.Handle {
    private lateinit var nextFeatureChain: FeatureChain.Handle

    override suspend fun handle(message: String) =
        if (featureChain.canHandle(message)) {
            featureChain.handle(message)
        } else {
            nextFeatureChain.handle(message)
        }


    fun setNextFeatureChain(featureChain: FeatureChain.CheckAndHandle) {
        nextFeatureChain = featureChain
    }
}