package com.github.johnnysc.ecp.ui.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.ecp.core.Mapper.UnitMapper

interface Communication<T> : Observe<T>, UnitMapper<T> {
    class Base<T> : Communication<T> {
        private val mutableLiveData: MutableLiveData<T> = MutableLiveData()

        override fun map(data: T) {
            mutableLiveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            mutableLiveData.observe(owner, observer)
        }
    }
}