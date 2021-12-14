package com.github.johnnysc.ecp.ui.dictionary

import com.github.johnnysc.ecp.ui.core.Communication

interface DictionaryCommunication : Communication<DictionaryUi> {
    class Base : Communication.Base<DictionaryUi>(), DictionaryCommunication
}