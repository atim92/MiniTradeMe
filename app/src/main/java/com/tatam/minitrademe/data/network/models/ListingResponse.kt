package com.tatam.minitrademe.data.network.models

import com.squareup.moshi.Json

data class ListingResponse(
	@field:Json(name="TotalCount") val totalCount: Int? = null,
	@field:Json(name="PageSize") val pageSize: Int? = null,
	@field:Json(name="Page") val page: Int? = null,
	@field:Json(name="List") val list: List<Listing>? = null
)