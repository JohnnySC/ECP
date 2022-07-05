package com.github.johnnysc.ecp.presentation.messages.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

//TODO remove redundant interface
interface MessageAdapter {
    class Base : GenericAdapter<MessageUI>(
        CorrectAiMessageViewHolderFactoryChain(
            ErrorAiMessageViewHolderFactoryChain(
                UserMessageViewHolderFactoryChain(
                    ViewHolderFactoryChain.Exception()
                )
            )
        )
    )
}