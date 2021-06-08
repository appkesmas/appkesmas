package id.ajiguna.appkesmas.core.network.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
