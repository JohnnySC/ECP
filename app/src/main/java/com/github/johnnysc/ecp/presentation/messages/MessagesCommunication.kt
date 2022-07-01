package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface MessagesCommunication {

    class Base(
        private val mutableLiveData: MutableLiveData<List<ItemUi>> = MutableLiveData()
    ) : MessagesCommunication, Communication.Observe<List<ItemUi>>, Communication.Update<ItemUi> {

        override fun observe(owner: LifecycleOwner, observer: Observer<List<ItemUi>>) =
            mutableLiveData.observe(owner, observer)

        override fun map(data: ItemUi) {
            val result = mutableListOf<ItemUi>()
            mutableLiveData.value?.let(result::addAll)
            result.add(data)
            mutableLiveData.value = result
        }
    }
}
