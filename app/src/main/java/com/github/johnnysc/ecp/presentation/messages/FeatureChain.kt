package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command

interface FeatureChain {
    interface Check : FeatureChain {
        fun canHandle(message: String): Boolean
    }

    interface Handle : FeatureChain {
        suspend fun handle(message: String): MessageUI
    }

    interface CheckAndHandle : Check, Handle {

        open class Base<T>(
            private val interactor: T,
            private val commands: List<Command<T>>
        ) : CheckAndHandle {

            private var currentCommand: Command<T> = Command.Empty()

            override fun canHandle(message: String): Boolean {
                val find = commands.find {
                    it.canHandle(message)
                }
                find?.let {
                    currentCommand = it
                }
                return find != null
            }

            override suspend fun handle(message: String): MessageUI {
                val result = currentCommand.handle(interactor)
                currentCommand = Command.Empty()
                return result
            }
        }
    }

    class UnknownMessageChain(private val manageResources: ManageResources) : CheckAndHandle {
        private val errorMessageId = "-1"

        override fun canHandle(message: String) = true

        override suspend fun handle(message: String) = MessageUI.AiError(
            manageResources.string(R.string.i_dont_understand)
        )
    }

    class Empty : Handle {
        override suspend fun handle(message: String) = MessageUI.Empty()
    }

}