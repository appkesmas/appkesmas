package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QueueResponse(

	@field:SerializedName("data")
	val data: DataQueue? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class DataQueue(

	@field:SerializedName("puskesmas_id")
	val puskesmasId: String? = null,

	@field:SerializedName("start_time")
	val startTime: String? = null,

	@field:SerializedName("immediacy")
	val immediacy: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("end_time")
	val endTime: String? = null,

	@field:SerializedName("temperature")
	val temperature: Int? = null,

	@field:SerializedName("prediction_time")
	val predictionTime: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("doctor_name")
	val doctorName: String? = null,

	@field:SerializedName("painscale")
	val painscale: Int? = null,

	@field:SerializedName("jenis_pengobatan")
	val jenisPengobatan: String? = null
) : Parcelable
