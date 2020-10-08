package com.gnrchmt.swapi.model


import com.google.gson.annotations.SerializedName

data class Starship(
    @SerializedName("name") override val name: String = "",
    @SerializedName("name") override val title: String = "",
    @SerializedName("model") val model: String = "",
    @SerializedName("manufacturer") val manufacturer: String = "",
    @SerializedName("cost_in_credits") val costInCredits: String = "",
    @SerializedName("length") val length: String = "",
    @SerializedName("max_atmosphering_speed") val maxAtmospheringSpeed: String = "",
    @SerializedName("crew") val crew: String = "",
    @SerializedName("passengers") val passengers: String = "",
    @SerializedName("cargo_capacity") val cargoCapacity: String = "",
    @SerializedName("consumables") val consumables: String = "",
    @SerializedName("hyperdrive_rating") val hyperdriveRating: String = "",
    @SerializedName("MGLT") val mGLT: String = "",
    @SerializedName("starship_class") val starshipClass: String = "",
    @SerializedName("pilots") val pilots: List<Any> = listOf(),
    @SerializedName("films") val films: List<String> = listOf(),
    @SerializedName("created") val created: String = "",
    @SerializedName("edited") val edited: String = "",
    @SerializedName("url") override val url: String = ""

) : IModel