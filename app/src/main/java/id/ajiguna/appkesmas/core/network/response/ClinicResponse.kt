package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClinicResponse (

   @field:SerializedName("address")
   val address: String? = null,

   @field:SerializedName("name")
   val name: String? = null,

   @field:SerializedName("id")
   val id: String? = null

) : Parcelable

