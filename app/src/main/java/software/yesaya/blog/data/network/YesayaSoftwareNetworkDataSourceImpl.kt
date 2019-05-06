package software.yesaya.blog.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import software.yesaya.blog.internal.AccessToken
import software.yesaya.blog.internal.NoConnectivityException

class YesayaSoftwareNetworkDataSourceImpl(
    private var email : String,
    private var password : String,
    private val yesayaSoftwareBuilder: YesayaSoftwareBuilder
) : YesayaSoftwareNetworkDataSource {
    override suspend fun getToken() {
        try {
            val accessToken = yesayaSoftwareBuilder.createService(YesayaSoftwareApiService::class.java)
                .login(email, password)

            savedToken.value
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override val savedToken: LiveData<AccessToken>
        get() {

        }
}