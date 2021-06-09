package id.ajiguna.appkesmas.core.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HospitaliResponse(

	@field:SerializedName("Ketersediaan Isolasi Tanpa Tekanan Negatif")
	val ketersediaanIsolasiTanpaTekananNegatif: Int? = null,

	@field:SerializedName("Ketersediaan HD khusus COVID-19")
	val ketersediaanHDKhususCOVID19: Int? = null,

	@field:SerializedName("Nama RS")
	val namaRS: String? = null,

	@field:SerializedName("Jarak")
	val jarak: Double? = null,

	@field:SerializedName("Ketersediaan OK khusus COVID-19")
	val ketersediaanOKKhususCOVID19: Int? = null,

	@field:SerializedName("Ketersediaan ICU Tekanan Negatif Dengan Ventilator")
	val ketersediaanICUTekananNegatifDenganVentilator: Int? = null,

	@field:SerializedName("Ketersediaan ICU Tanpa Tekanan Negatif dengan Ventilator")
	val ketersediaanICUTanpaTekananNegatifDenganVentilator: Int? = null,

	@field:SerializedName("Image URL")
	val imageURL: String? = null,

	@field:SerializedName("Ketersediaan ICU Tekanan Negatif Tanpa Ventilator")
	val ketersediaanICUTekananNegatifTanpaVentilator: Int? = null,

	@field:SerializedName("Ketersediaan NICU khusus COVID-19")
	val ketersediaanNICUKhususCOVID19: Int? = null,

	@field:SerializedName("Ketersediaan Perina khusus COVID-19")
	val ketersediaanPerinaKhususCOVID19: Int? = null,

	@field:SerializedName("Rank")
	val rank: Int? = null,

	@field:SerializedName("Hotline SPGDT")
	val hotlineSPGDT: String? = null,

	@field:SerializedName("Kode RS")
	val kodeRS: String? = null,

	@field:SerializedName("Ketersediaan PICU khusus COVID-19")
	val ketersediaanPICUKhususCOVID19: Int? = null,

	@field:SerializedName("Total Ketersediaan")
	val totalKetersediaan: Int? = null,

	@field:SerializedName("Wilayah")
	val wilayah: String? = null,

	@field:SerializedName("Kelas")
	val kelas: String? = null,

	@field:SerializedName("Hospital ID")
	val hospitalID: String? = null,

	@field:SerializedName("Jenis RS")
	val jenisRS: String? = null,

	@field:SerializedName("Ketersediaan ICU Tanpa Tekanan Negatif tanpa Ventilator")
	val ketersediaanICUTanpaTekananNegatifTanpaVentilator: Int? = null,

	@field:SerializedName("Ketersediaan Isolasi Tekanan Negatif")
	val ketersediaanIsolasiTekananNegatif: Int? = null
) : Parcelable
