package com.gnrchmt.swapi.model


import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("name") override val name: String = "",
    @SerializedName("name") override val title: String = "",
    @SerializedName("rotation_period") val rotationPeriod: String = "",
    @SerializedName("orbital_period") val orbitalPeriod: String = "",
    @SerializedName("diameter") val diameter: String = "",
    @SerializedName("climate") val climate: String = "",
    @SerializedName("gravity") val gravity: String = "",
    @SerializedName("terrain") val terrain: String = "",
    @SerializedName("surface_water") val surfaceWater: String = "",
    @SerializedName("population") val population: String = "",
    @SerializedName("residents") val residents: List<String> = listOf(),
    @SerializedName("films") val films: List<String> = listOf(),
    @SerializedName("created") val created: String = "",
    @SerializedName("edited") val edited: String = "",
    @SerializedName("url") override val url: String = ""

) : IModel