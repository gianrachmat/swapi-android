package com.gnrchmt.swapi.utils

import java.util.*

const val BASE_URL = "https://swapi.dev/api/"
const val BASE_URL_HTTP = "http://swapi.dev/api/"

const val TIME_OUT = 20L

const val ERROR_MESSAGE = "Connection Problem"
const val KEY_RESULTS = "results"
const val KEY_COUNT = "count"
const val KEY_NEXT = "next"

enum class TAGS (string: String) {
    TAG("Tag"),
    GET_UPDATE("Get Update"),
    GET_SERVICE("Get Service"),
    GET_PEOPLE("Get People"),
    GET_PLANETS("Get Planets"),
    GET_FILMS("Get Films"),
    GET_SPECIES("Get Species"),
    GET_VEHICLES("Get Vehicles"),
    GET_STARSHIPS("Get Starships");

    companion object {
        fun getByName(name: String?) = valueOf((name ?: "").toUpperCase(Locale.ROOT))
    }
}