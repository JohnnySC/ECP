package com.github.johnnysc.ecp.schedule.domain.exception

import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.DomainException

class ThereIsNoSuchEvent : DomainException.AbstractDomainException(R.string.there_is_no_event)