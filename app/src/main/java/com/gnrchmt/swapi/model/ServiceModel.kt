package com.gnrchmt.swapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceModel(
    val service: String = "",
    val url: String = ""
) : Parcelable