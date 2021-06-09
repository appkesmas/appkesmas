package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryResponse(

	@field:SerializedName("puskesmas_id")
	val puskesmasId: String? = null,

	@field:SerializedName("start_time")
	val startTime: String? = null,

	@field:SerializedName("immediacy")
	val immediacy: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("puskesmas")
	val puskesmas: Puskesmas? = null,

	@field:SerializedName("end_time")
	val endTime: String? = null,

	@field:SerializedName("temperature")
	val temperature: Int? = null,

	@field:SerializedName("prediction_time")
	val predictionTime: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("painscale")
	val painscale: Int? = null
) : Parcelable

@Parcelize
data class Puskesmas(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
