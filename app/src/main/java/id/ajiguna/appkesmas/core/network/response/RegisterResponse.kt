package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("sex")
	val sex: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("medical_id")
	val medicalId: String? = null,

	@field:SerializedName("identity_number")
	val identityNumber: String? = null,

	@field:SerializedName("age")
	val age: String? = null
) : Parcelable
