package com.github.johnnysc.ecp.sl.view_model_chain

import com.github.johnnysc.ecp.presentation.messages.FeatureChain

interface FeatureChainDependencyContainer {

    fun <T : FeatureChain.CheckAndHandle> featureChain(clazz: Class<T>): FeatureChainModule<T>

    class Error : FeatureChainDependencyContainer {
        override fun <T : FeatureChain.CheckAndHandle> featureChain(clazz: Class<T>): FeatureChainModule<T> {
            throw IllegalStateException("no module found for $clazz")
        }
    }
}