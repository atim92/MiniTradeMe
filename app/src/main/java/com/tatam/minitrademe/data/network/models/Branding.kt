package com.tatam.minitrademe.data.network.models

import com.squareup.moshi.Json

data class Branding(
    @field:Json(name = "LargeBannerURL") val largeBannerURL: String? = null,
    @field:Json(name = "LargeWideLogo") val largeWideLogo: String? = null
)