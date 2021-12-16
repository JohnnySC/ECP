package com.github.johnnysc.ecp.ui.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.ecp.core.Mapper.UnitMapper

interface Communication<T> : Observe<T>, UnitMapper<T> {
    abstract class Base<T : Any> : Communication<T> {
        private val mutableLiveData: MutableLiveData<T> = MutableLiveData()

        override fun map(data: T) {
            mutableLiveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            mutableLiveData.observe(owner, observer)
        }
    }

    class Empty : Communication<Nothing> {
        override fun map(data: Nothing) = Unit

        override fun observe(owner: LifecycleOwner, observer: Observer<Nothing>) = Unit
    }

    abstract class Single<T : Any> : Communication<T> {
        private val singleLiveEvent = SingleLiveEvent<T>()

        override fun map(data: T) {
            singleLiveEvent.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            singleLiveEvent.observe(owner, observer)
    }
}
