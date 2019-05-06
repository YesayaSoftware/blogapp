package software.yesaya.blog.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.yesayasoftware.learning.data.network.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import software.yesaya.blog.BuildConfig

private const val BASE_URL = "http://dev.yesaya.software/api/"

class YesayaSoftwareBuilder(
    private var connectivityInterceptor: ConnectivityInterceptor
)   {
     fun service() : YesayaSoftwareApiService {
        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(connectivityInterceptor)

        if (BuildConfig.DEBUG)
            okHttpClient.addNetworkInterceptor(StethoInterceptor())

        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YesayaSoftwareApiService::class.java)
    }

    private val client = buildClient()
    private val retrofit = buildRetrofit(client)

    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()

                val builder = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Connection", "close")

                request = builder.build()

                chain.proceed(request)
            }

        if (BuildConfig.DEBUG)
            builder.addNetworkInterceptor(StethoInterceptor())

        return builder.build()

    }

    private fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun getRetrofits(): Retrofit {
        return retrofit
    }
}