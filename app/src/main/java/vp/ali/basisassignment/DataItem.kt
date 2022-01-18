package vp.ali.basisassignment

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)
