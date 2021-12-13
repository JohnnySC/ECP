package com.github.johnnysc.ecp.data.dictionary

interface DefinitionMapper<T> {
    fun map(
        definition: String,
        example: String,
        synonyms: List<String>?,
        antonyms: List<String>?
    ): T
}