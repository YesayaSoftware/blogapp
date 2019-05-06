package software.yesaya.blog.data.repository

import android.content.SharedPreferences
import com.yesayasoftware.learning.data.db.dao.PostDao
import software.yesaya.blog.data.network.YesayaSoftwareNetworkDataSource
import software.yesaya.blog.internal.AccessToken

class YesayaSoftwareRepositoryImpl(
    private val prefs : SharedPreferences,
    private val postDao : PostDao,
    private val yesayasoftwareNetworkDataSource: YesayaSoftwareNetworkDataSource
) : YesayaSoftwareRepository {
    private val editor: SharedPreferences.Editor = prefs.edit()

    override val token: AccessToken
        get() {
            val token = AccessToken()
            token.accessToken = prefs.getString("ACCESS_TOKEN", null)
            token.refreshToken = prefs.getString("REFRESH_TOKEN", null)
            return token
        }

    init {
        yesayasoftwareNetworkDataSource.apply {
            savedToken.observeForever { newToken ->
                saveToken(newToken)
            }
        }
    }

    private fun saveToken(token: AccessToken) {
        editor.putString("ACCESS_TOKEN", token.accessToken).commit()
        editor.putString("REFRESH_TOKEN", token.refreshToken).commit()
    }
}