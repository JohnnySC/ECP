package com.github.johnnysc.ecp.presentation.messages.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain
import com.github.johnnysc.ecp.R

class ErrorAiMessageViewHolderFactoryChain(private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>) :
    ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == 3) {
            CorrectAiMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.ai_incorrect_message, parent, false)
            )
        } else viewHolderFactoryChain.viewHolder(parent, viewType)
}