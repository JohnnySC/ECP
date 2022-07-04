package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.presentation.Communication

interface MessagesCommunication {

    interface Observe : Communication.Observe<List<MessageUI>>
    interface Update : Communication.Update<MessageUI>

    interface Mutable : Observe, Update

    class Base(
        private val mutableLiveData: MutableLiveData<List<MessageUI>> = MutableLiveData(),
        private val mapper: Mapper = Mapper.Base()
    ) : Mutable {

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) =
            mutableLiveData.observe(owner, observer)

        override fun map(data: MessageUI) {
            mutableLiveData.value = mapper.map(data, mutableLiveData.value)
        }
    }

    interface Mapper {

        fun map(messageUI: MessageUI, list: List<MessageUI>?): List<MessageUI>

        class Base : Mapper {

            override fun map(messageUI: MessageUI, list: List<MessageUI>?): List<MessageUI> {
                val result = mutableListOf<MessageUI>()
                list?.let(result::addAll)
                val id = result.size.toString()
                result.add(messageUI.copyWithId(id))
                return result
            }
        }
    }
}