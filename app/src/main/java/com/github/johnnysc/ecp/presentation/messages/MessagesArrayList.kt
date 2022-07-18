package com.github.johnnysc.ecp.presentation.messages

class MessagesArrayList : ArrayList<MessageUI>() {

    override fun add(element: MessageUI): Boolean {
        val id = this.size.toString()
        return super.add(element.copyWithId(id))
    }
}