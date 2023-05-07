package net.ophalvens.composeproductsdemo.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.POST


private const val BASE_URL = "https://stevenop.be/wm/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL)
    .build()

object ProductenApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {

    /* het /PRODUCTSget.php endpoint */
    @POST("PRODUCTSget.php")
    suspend fun getProducten(): ApiResponseProductsGet
}