package id.ajiguna.appkesmas.core.network

import id.ajiguna.appkesmas.core.network.response.*
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface ApiService {
    @GET("puskesmas/")
    fun getClinic(): Call<List<ClinicResponse>>

    @GET("hospital/")
    fun getHospital(): Call<List<HospitalResponse>>

    @GET("indonesia/")
    fun getCovid(): Call<List<CovidResponse>>

    @GET("user/{id}")
    fun getUser(
        @Path("id") id: String,
    ): Call<UserResponse>


    @GET("user/{id_user}/treatment/")
    fun getHistory(
        @Path("id_user") id: String,
    ): Call<List<CovidResponse>>


    @FormUrlEncoded
    @POST("user/")
    fun postRegister(
            @FieldMap params: HashMap<String, String>?
    ): Call<RegisterResponse>
}