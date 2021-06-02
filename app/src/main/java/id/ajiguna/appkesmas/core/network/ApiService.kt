package id.ajiguna.appkesmas.core.network

import id.ajiguna.appkesmas.core.network.response.ClinicResponse
import id.ajiguna.appkesmas.core.network.response.CovidResponse
import id.ajiguna.appkesmas.core.network.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface ApiService {
    @GET("puskesmas/")
    fun getClinic(): Call<List<ClinicResponse>>

    @GET("hospital/")
    fun getHospital(): Call<List<ClinicResponse>>

    @GET("indonesia/")
    fun getCovid(): Call<List<CovidResponse>>

    @FormUrlEncoded
    @POST("user/")
    fun postRegister(
            @FieldMap params: HashMap<String, String>?
    ): Call<RegisterResponse>
}