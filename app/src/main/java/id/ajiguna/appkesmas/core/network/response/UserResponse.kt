package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class Puskesmas(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("bpjs_number")
	val bpjsNumber: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("puskesmas")
	val puskesmas: Puskesmas,

	@field:SerializedName("sex")
	val sex: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("identity_number")
	val identityNumber: String,

	@field:SerializedName("age")
	val age: Int
) : Parcelable
