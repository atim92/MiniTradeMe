package com.tatam.minitrademe.data.network.models

import com.squareup.moshi.Json

data class GeographicLocation(
	@field:Json(name="Accuracy") val accuracy: Int? = null,
	@field:Json(name="Northing") val northing: Int? = null,
	@field:Json(name="Latitude") val latitude: Double? = null,
	@field:Json(name="Easting") val easting: Int? = null,
	@field:Json(name="Longitude") val longitude: Double? = null
)