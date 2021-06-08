package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceiptResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("puskesmas")
	val puskesmas: String? = null,

	@field:SerializedName("drug_list")
	val drugList: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("doctor_name")
	val doctorName: String? = null,

	@field:SerializedName("user")
	val user: String? = null
): Parcelable
