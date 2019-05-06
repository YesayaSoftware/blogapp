package software.yesaya.blog.data.repository

import androidx.lifecycle.LiveData
import software.yesaya.blog.internal.AccessToken

interface YesayaSoftwareRepository {
    val token: AccessToken
}