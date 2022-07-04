package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface MessageUI : ItemUi {

    fun copyWithId(id: String): MessageUI

    abstract class Message(private val id: String, private val text: String) : MessageUI {
        override fun content() = text

        override fun id() = id

        override fun show(vararg views: MyView) {
            views[0].show(text)
        }
    }

    data class User(private val id: String, private val text: String) : Message(id, text) {
        override fun type() = 1

        override fun copyWithId(id: String) = copy(id = id)
    }

    data class Ai(private val id: String, private val text: String) : Message(id, text) {
        override fun type() = 2

        override fun copyWithId(id: String) = copy(id = id)
    }

    data class AiError(private val id: String, private val text: String) : Message(id, text) {
        override fun type() = 3

        override fun copyWithId(id: String) = copy(id = id)
    }

    data class Empty(
        private val id: String = "",
        private val text: String = ""
    ) : Message(id, text) {

        override fun type() = Int.MIN_VALUE

        override fun copyWithId(id: String) = copy(id = id)
    }
}