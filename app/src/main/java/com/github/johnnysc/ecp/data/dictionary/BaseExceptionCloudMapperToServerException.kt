package com.github.johnnysc.ecp.data.dictionary

import com.github.johnnysc.ecp.data.core.ServerExceptionData

class BaseExceptionCloudMapperToServerException : ExceptionCloud.Mapper<ServerExceptionData> {
    override fun map(tile: String, message: String, resolution: String) =
        ServerExceptionData("$tile\n$message\n$resolution")
}