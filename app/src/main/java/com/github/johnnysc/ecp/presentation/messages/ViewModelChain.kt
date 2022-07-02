package com.github.johnnysc.ecp.presentation.messages

class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle,
) : FeatureChain.Handle {
    private var nextFeatureChain: FeatureChain.Handle = FeatureChain.Empty()

    override suspend fun handle(message: String) =
        if (featureChain.canHandle(message))
            featureChain.handle(message)
        else
            nextFeatureChain.handle(message)

    fun setNextFeatureChain(featureChain: FeatureChain.Handle) {
        nextFeatureChain = featureChain
    }

}