package com.github.johnnysc.ecp.presentation.messages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class ErrorAiMessageViewHolderFactoryChain(private val viewHolderFactoryChain: ViewHolderFactoryChain<MessageUI>) :
    ViewHolderFactoryChain<MessageUI> {

    override fun viewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == 3) {
            ErrorAiMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.ai_incorrect_message, parent, false)
            )
        } else viewHolderFactoryChain.viewHolder(parent, viewType)
}