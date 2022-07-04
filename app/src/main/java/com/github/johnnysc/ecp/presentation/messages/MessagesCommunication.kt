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
        private val mutableLiveData: MutableLiveData<List<MessageUI>> = MutableLiveData()
    ) : Mutable {

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) =
            mutableLiveData.observe(owner, observer)

        override fun map(data: MessageUI) {
            val result = mutableListOf<MessageUI>()
            mutableLiveData.value?.let(result::addAll)
            val id = result.size.toString()
            result.add(data.copyWithId(id))
            mutableLiveData.value = result
        }
    }
}