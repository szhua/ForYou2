package com.szhua.foryou.api
import com.szhua.foryou.data.BMobDiariesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import  okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BMobService {

    @GET("classes/diary")
    suspend fun  findDiaries(@Query("limit")limit:Int,@Query("skip")skip:Int ,@Query("order")orders:String ) : BMobDiariesResponse

    companion object{
        private const val  BASE_URL = "https://api2.bmob.cn/1/"
        fun create() : BMobService{
              val logger = HttpLoggingInterceptor().apply { level = Level.BODY }
              val client = OkHttpClient.Builder()
//                  .hostnameVerifier { _, _ -> true }
//                  .sslSocketFactory(
//                      SslUtils.generateSSLSocketFactory(),
//                      SslUtils.generateTrustManager()[0] as X509TrustManager
//                  )
                  .addInterceptor(BMobHeaderInterceptor())
                  .addInterceptor(logger)
                  .build()
               return  Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .client(client)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()
                   .create(BMobService::class.java)
        }
    }

}