package com.github.johnnysc.ecp.presentation.messages

abstract class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle<Boolean>,
) : FeatureChain.Check<MessageUI> {

    protected var nextFeatureChain: FeatureChain.Check<MessageUI> = FeatureChain.Empty()

    override suspend fun canHandle(message: String) =
        if (featureChain.canHandle(message))
            featureChain.handle()
        else
            nextFeatureChain.canHandle(message)

    fun nextFeatureChain(featureChain: FeatureChain.Check<MessageUI>) {
        nextFeatureChain = featureChain
    }
}