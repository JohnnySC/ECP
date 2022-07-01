package com.github.johnnysc.ecp.presentation.messages.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

interface MessageAdapter {
    class Base : GenericAdapter.Base(
        CorrectAiMessageViewHolderFactoryChain(
            ErrorAiMessageViewHolderFactoryChain(
                UserMessageViewHolderFactoryChain(
                    ViewHolderFactoryChain.Exception()
                )
            )
        )
    )
}