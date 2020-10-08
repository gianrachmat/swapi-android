package com.gnrchmt.swapi.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name") override val name: String = "",
    @SerializedName("name") override val title: String = "",
    @SerializedName("height") val height: String = "",
    @SerializedName("mass") val mass: String = "",
    @SerializedName("hair_color") val hairColor: String = "",
    @SerializedName("skin_color") val skinColor: String = "",
    @SerializedName("eye_color") val eyeColor: String = "",
    @SerializedName("birth_year") val birthYear: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("homeworld") val homeworld: String = "",
    @SerializedName("films") val films: List<String> = listOf(),
    @SerializedName("species") val species: List<Any> = listOf(),
    @SerializedName("vehicles") val vehicles: List<String> = listOf(),
    @SerializedName("starships") val starships: List<String> = listOf(),
    @SerializedName("created") val created: String = "",
    @SerializedName("edited") val edited: String = "",
    @SerializedName("url") override val url: String = ""
) : IModel