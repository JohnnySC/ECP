package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface MessagesUI : Mapper.Unit<Mapper.Unit<List<ItemUi>>> {

    class Base(private val listOfMessages: List<ItemUi>) : MessagesUI {

        override fun map(data: Mapper.Unit<List<ItemUi>>) {
            data.map(listOfMessages)
        }
    }
}