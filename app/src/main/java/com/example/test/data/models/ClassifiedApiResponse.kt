package com.example.test.data.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ClassifiedItem (
    @Json(name = "id")
    val id: Int?,
    @Json(name = "created_at")
    val created_at: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "image_urls")
    val imagesList: List<String>
) : Parcelable

data class ClassifiedApiResponse (val results: List<ClassifiedItem>) : Serializable