package com.github.johnnysc.ecp.presentation.messages.adapter

import android.view.View
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI

abstract class AbstractMessageViewHolder(view: View) : GenericViewHolder<MessageUI>(view) {

    override fun bind(item: MessageUI) {
        item.show(itemView.findViewById(R.id.messageTextView))
    }
}