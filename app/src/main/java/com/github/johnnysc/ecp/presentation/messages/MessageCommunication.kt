package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.presentation.Communication

interface MessageCommunication:Communication.Mutable<MessagesUI>{
    class Base:Communication.UiUpdate<MessagesUI>(),MessageCommunication
}