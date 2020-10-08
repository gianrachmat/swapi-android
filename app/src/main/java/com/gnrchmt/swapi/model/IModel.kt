package com.gnrchmt.swapi.model

interface IModel {
    val name: String?
    val title: String?
    val url: String
}

data class ModelData(
    override val name: String?,
    override val title: String?,
    override val url: String
) : IModel