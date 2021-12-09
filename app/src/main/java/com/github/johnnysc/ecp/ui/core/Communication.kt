package com.github.johnnysc.ecp.ui.core

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.ecp.core.Mapper.UnitMapper
import java.util.concurrent.atomic.AtomicBoolean

interface Communication<T> : Observe<T>, UnitMapper<T> {
    class Base<T : Any> : Communication<T> {
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

    class Single<T : Any> : Communication<T> {
        private val mutableLiveData = MutableLiveData<T>()
        private val pending = AtomicBoolean(false)

        @MainThread
        override fun map(data: T) {
            pending.set(true)
            mutableLiveData.value = data
        }

        @MainThread
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            mutableLiveData.observe(owner) {
                if (pending.compareAndSet(true, false)) {
                    observer.onChanged(it)
                }
            }
        }
    }
}