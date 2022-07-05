package com.github.johnnysc.ecp.presentation.messages.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class MessageAdapter : GenericAdapter<MessageUI>(
    CorrectAiMessageViewHolderFactoryChain(
        ErrorAiMessageViewHolderFactoryChain(
            UserMessageViewHolderFactoryChain(
                ViewHolderFactoryChain.Exception()
            )
        )
    )
)