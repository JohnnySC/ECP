package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.domain.ViewModelExceptionChain

interface ProvideViewModelExceptionChain<T:ViewModelExceptionChain> {
    fun viewModelExceptionChain():T

    class Base(vararg val viewModelExceptionChain: ProvideViewModelExceptionChain<out ViewModelExceptionChain>):ProvideViewModelExceptionChain<ViewModelExceptionChain>
    {
        override fun viewModelExceptionChain(): ViewModelExceptionChain {
            val listOfViewModelExceptionChain = viewModelExceptionChain.map { provideViewModelChain ->
                provideViewModelChain.viewModelExceptionChain()
            }

            val finalChain = listOfViewModelExceptionChain.first()

            listOfViewModelExceptionChain.forEachIndexed { index, viewModelExceptionChain ->
                if (index < listOfViewModelExceptionChain.lastIndex)
                    viewModelExceptionChain.nextExceptionChain(listOfViewModelExceptionChain[index + 1])

            }
            return finalChain
        }

    }
}