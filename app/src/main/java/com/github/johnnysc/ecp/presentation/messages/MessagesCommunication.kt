package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface MessagesCommunication : Communication.Observe<List<MessageUI>>,
    Communication.Update<MessageUI> {

    class Base(
        private val mutableLiveData: MutableLiveData<List<MessageUI>>
    ) : MessagesCommunication {

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) =
            mutableLiveData.observe(owner, observer)

        override fun map(data: MessageUI) {
            val result = mutableListOf<MessageUI>()
            mutableLiveData.value?.let(result::addAll)
            result.add(data)
            mutableLiveData.value = result
        }
    }
}