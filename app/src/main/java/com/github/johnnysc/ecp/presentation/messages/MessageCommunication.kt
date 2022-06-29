package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.presentation.Communication

//todo require some extra work
interface MessageCommunication : Communication.Mutable<MessagesUI> {
    class Base : Communication.UiUpdate<MessagesUI>(), MessageCommunication
}