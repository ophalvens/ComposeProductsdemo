package net.ophalvens.composeproductsdemo.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
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

    /* het /PRODUCTSadd.php endpoint */
    @Headers("Content-Type: application/json")
    @POST("PRODUCTSadd.php")
    suspend fun addProduct(@Body productData: ProductToSend): ApiResponseProductAdd

    /* het /PRODUCTSdelete.php endpoint */
    // Hier wordt @POST gebruikt ipv @DELETE, om de bestaande apps en voobeelden niet te breken.
    //
    // Het volledige Product wordt nu meegegeven, maar in de API wordt in het endpoint enkel naar
    // PR_ID gekeken voor de DELETE
    @Headers("Content-Type: application/json")
    @POST("PRODUCTSdelete.php")
    suspend fun deleteProduct(@Body productData: Product): ApiResponseProductDelete

}