package com.github.johnnysc.ecp.sl.view_model_chain

import com.github.johnnysc.ecp.presentation.messages.FeatureChain

interface FeatureChainModule<T : FeatureChain.CheckAndHandle> {

    fun featureChain(): T

}
