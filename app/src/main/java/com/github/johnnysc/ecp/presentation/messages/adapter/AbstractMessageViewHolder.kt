package com.github.johnnysc.ecp.presentation.messages.adapter

import android.view.View
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.ecp.R

abstract class AbstractMessageViewHolder(view: View) : GenericViewHolder<ItemUi>(view) {

    override fun bind(item: ItemUi) {
        item.show(itemView.findViewById(R.id.messageTextView))
    }
}