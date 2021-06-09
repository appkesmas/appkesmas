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

    @GET("banner/")
    fun getBanner(): Call<List<BannerResponse>>

    @GET("article/")
    fun getArticle(): Call<List<ArticleResponse>>

    @GET("user/{id}")
    fun getUser(
        @Path("id") id: String?,
    ): Call<UserResponse>

    @GET("treatment/{id}")
    fun getQueue(
        @Path("id") id: String?,
    ): Call<QueueResponse>

    @GET("user/{id_user}/prescription/")
    fun getReceiptUser(
        @Path("id_user") id: String,
    ): Call<List<ReceiptResponse>>

    @GET("user/{id_user}/treatment/")
    fun getHistory(
        @Path("id_user") id: String?,
    ): Call<List<HistoryResponse>>

    @FormUrlEncoded
    @POST("user/")
    fun postRegister(
            @FieldMap params: HashMap<String, String>?
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("treatment/")
    fun postTreatment(
        @FieldMap params: HashMap<String, String>?
    ): Call<TreadmentResponse>

}