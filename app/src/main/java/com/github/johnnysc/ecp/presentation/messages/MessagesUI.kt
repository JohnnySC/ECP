package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.core.Mapper as UnitMapper

interface MessagesUI:UnitMapper.Unit<UnitMapper.Unit<List<ItemUi>>> {
    fun<T> map(mapper: Mapper<T>):T

    class Base(private val listOfMessages:List<MessageUI>):MessagesUI {

        override fun map(data: UnitMapper.Unit<List<ItemUi>>) {
            data.map(listOfMessages)
        }

        override fun <T> map(mapper: Mapper<T>) = mapper.map(listOfMessages)

    }

    interface Mapper<T>
    {
        fun map(items:List<ItemUi>):T
    }
}