package com.github.johnnysc.ecp.presentation.messages

class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle,
    private  val nextFeatureChain: FeatureChain.Handle
) : FeatureChain.Handle {


    override suspend fun handle(message: String) =
        if (featureChain.canHandle(message))
            featureChain.handle(message)
         else
            nextFeatureChain.handle(message)

}