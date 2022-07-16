package com.github.johnnysc.ecp.presentation.messages

class MessagesArrayList : ArrayList<MessageUI>() {

    override fun add(element: MessageUI): Boolean {
        val id =this.size.toString()
        super.add(0,element.copyWithId(id))
        return true
    }
}