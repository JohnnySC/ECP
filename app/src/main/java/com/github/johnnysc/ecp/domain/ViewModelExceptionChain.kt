package com.github.johnnysc.ecp.domain

abstract class ViewModelExceptionChain(private val exceptionChain: ExceptionChain.CheckAndHandle):ExceptionChain.Handle {
    protected var nextExceptionChain:ExceptionChain.Handle=ExceptionChain.Empty()

    override fun handle(error: Exception): Exception {
        var result:Exception
        if(exceptionChain.check(exception = error))
        {
            result=exceptionChain.handle(error)
        }
        else
        {
            result=nextExceptionChain.handle(error)
        }
        return result
    }

    fun nextExceptionChain(exceptionChain: ExceptionChain.Handle)
    {
        nextExceptionChain=exceptionChain
    }
}