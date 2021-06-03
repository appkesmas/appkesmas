package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HospitalResponse(

	@field:SerializedName("area")
	val area: String? = null,

	@field:SerializedName("image_name")
	val imageName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("class_type")
	val classType: String? = null,

	@field:SerializedName("bed_availability")
	val bedAvailability: Int? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
) : Parcelable
