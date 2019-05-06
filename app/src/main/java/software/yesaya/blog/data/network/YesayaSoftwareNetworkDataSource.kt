package software.yesaya.blog.data.network

import androidx.lifecycle.LiveData
import software.yesaya.blog.internal.AccessToken

interface YesayaSoftwareNetworkDataSource {
    val savedToken: LiveData<AccessToken>

    suspend fun getToken()
}