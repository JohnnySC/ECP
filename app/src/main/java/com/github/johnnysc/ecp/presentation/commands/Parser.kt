package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.coremvvm.core.Mapper

interface Parser<T> : Mapper<String, IsEmptyUseCase<T>>