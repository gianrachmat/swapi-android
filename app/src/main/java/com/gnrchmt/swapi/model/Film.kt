package com.gnrchmt.swapi.model


import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("title") override val name: String = "",
    @SerializedName("title") override val title: String = "",
    @SerializedName("episode_id") val episodeId: Int = 0,
    @SerializedName("opening_crawl") val openingCrawl: String = "",
    @SerializedName("director") val director: String = "",
    @SerializedName("producer") val producer: String = "",
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("characters") val characters: List<String> = listOf(),
    @SerializedName("planets") val planets: List<String> = listOf(),
    @SerializedName("starships") val starships: List<String> = listOf(),
    @SerializedName("vehicles") val vehicles: List<String> = listOf(),
    @SerializedName("species") val species: List<String> = listOf(),
    @SerializedName("created") val created: String = "",
    @SerializedName("edited") val edited: String = "",
    @SerializedName("url") override val url: String = ""
) : IModel