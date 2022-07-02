package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.coremvvm.core.Dispatchers

abstract class ViewModelChain(
    private val featureChain: FeatureChain.CheckAndHandle,
    private val dispatchers: Dispatchers,
    private val messagesCommunication: MessagesCommunication.Update
) : ViewModel(), FeatureChain.Check {
    var nextFeatureChain: MutableList<FeatureChain.CheckAndHandle> = mutableListOf()

    override fun canHandle(message: String): Boolean {
        var result = false
        if (featureChain.canHandle(message)) {
            result = true
            handleMessage(featureChain, message)
        } else {
            for (featureChain in nextFeatureChain) {
                if (featureChain.canHandle(message)) {
                    handleMessage(featureChain, message)
                    result = true
                    break
                }
            }
        }
        return result
    }

    private fun handleResult(messageUI: MessageUI) {
        messagesCommunication.map(messageUI)
    }

    fun handleMessage(featureChain: FeatureChain.CheckAndHandle, message: String) {
        dispatchers.launchBackground(viewModelScope)
        {
            val messageResult = featureChain.handle(message)
            dispatchers.changeToUI {
                handleResult(messageResult)
            }
        }
    }

    fun setFeatureChain(featureChain: FeatureChain.CheckAndHandle) {
        nextFeatureChain.add(featureChain)
    }
}