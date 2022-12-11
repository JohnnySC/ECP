package com.github.johnnysc.ecp.schedule.domain.exception

import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.ExceptionChain

class ThereIsNoSuchEventChain(exceptionChain: ExceptionChain) : ExceptionChain.Base(exceptionChain) {

    override val exceptionClass = ThereIsNoSuchEventException::class.java

    override fun createDomainException(): DomainException = ThereIsNoSuchEvent()
}