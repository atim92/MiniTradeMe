package com.tatam.minitrademe.data.network.models

import com.squareup.moshi.Json

data class AdditionalData(
    @field:Json(name = "BulletPoints") val bulletPoints: List<Any?>? = null,
    @field:Json(name = "Tags") val tags: List<Any?>? = null
)