package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CovidResponse(

	@field:SerializedName("meninggal")
	val meninggal: String? = null,

	@field:SerializedName("positif")
	val positif: String? = null,

	@field:SerializedName("sembuh")
	val sembuh: String? = null,

	@field:SerializedName("name")
	val name: String? = null
) : Parcelable
