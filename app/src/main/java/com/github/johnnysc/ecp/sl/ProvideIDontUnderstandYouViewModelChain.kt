package com.github.johnnysc.ecp.sl

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.UnknownMessageViewModelChain

class ProvideIDontUnderstandYouViewModelChain(private val manageResources: ManageResources) :
    ProvideViewModelChain<UnknownMessageViewModelChain> {

    override fun viewModelChain() = UnknownMessageViewModelChain(FeatureChain.UnknownMessageChain(manageResources))
}