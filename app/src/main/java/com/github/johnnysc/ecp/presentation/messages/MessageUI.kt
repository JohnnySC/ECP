package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface MessageUI : ItemUi {
    abstract class Message(private val id: String, private val text: String) : MessageUI {
        override fun content() = text

        override fun id() = id

        override fun show(vararg views: MyView) {
            views[0].show(text)
        }
    }

    class UserMessageUI(id: String, text: String) : Message(id, text) {
        override fun type() = 1
    }

    class AiMessageUI(id: String, text: String) : Message(id, text) {
        override fun type() = 2
    }

    class AiErrorMessageUI(id: String, text: String) : Message(id, text) {
        override fun type() = 3
    }
}