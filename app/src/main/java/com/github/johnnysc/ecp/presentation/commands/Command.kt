package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

/**
 * @param T Interactor
 */
interface Command<T> : FeatureChain.Check, HandleUseCase<T> {

//    abstract class Abstract<T> : Command<T> {
//
//        protected abstract val triggers: List<String>
//
//        protected abstract val excluded: List<String>
//
//        protected abstract val ignored: List<String>
//
//        protected val data = mutableListOf<String>()
//
//        override fun canHandle(message: String): Boolean {
//            var canHandle = false
//            message.split(" ").find {
//                triggers.contains(it)
//            }?.let {
//                canHandle = true
//                parseData(message)
//            }
//            return canHandle
//        }
//
//        private fun parseData(data: String) {
//            val cleanedData = data.apply {
//                ignored.forEach { ignoredSymbols ->
//                    this.replace(ignoredSymbols, "")
//                }
//            }
//            val dataList = cleanedData.split(" ").toMutableList()
//            excluded.forEach {
//                dataList.remove(it)
//            }
//            this.data.addAll(dataList)
//        }
//    }

    class Empty<T> : Command<T> {
        override suspend fun handle(useCase: T): MessageUI {
            return MessageUI.Empty()
        }

        override fun canHandle(message: String): Boolean {
            return false
        }
    }
}

interface HandleUseCase<T> {
    suspend fun handle(useCase: T): MessageUI
}