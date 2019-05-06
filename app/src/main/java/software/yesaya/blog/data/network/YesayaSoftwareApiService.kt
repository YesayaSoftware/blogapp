package software.yesaya.blog.data.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import software.yesaya.blog.internal.AccessToken

interface YesayaSoftwareApiService {
    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email") username: String, @Field("password") password: String): Call<AccessToken>
}