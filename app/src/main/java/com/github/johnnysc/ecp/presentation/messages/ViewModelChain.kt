package com.github.johnnysc.ecp.presentation.messages

abstract class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle,
) : FeatureChain.Handle {
    protected var nextFeatureChain: FeatureChain.Handle = FeatureChain.Empty()

    override suspend fun handle(message: String) =
        if (featureChain.canHandle(message))
            featureChain.handle(message)
        else
            nextFeatureChain.handle(message)

     @JvmName("setNextFeatureChain1")
     fun setNextFeatureChain(featureChain: FeatureChain.Handle) {
        nextFeatureChain = featureChain
    }

}