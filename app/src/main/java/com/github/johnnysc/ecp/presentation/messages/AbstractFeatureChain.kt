package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.ecp.presentation.commands.Command

abstract class AbstractFeatureChain<T>(
    private val interactor: T,
    private val commands: List<Command<T>>
) : FeatureChain.CheckAndHandle {

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