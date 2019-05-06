package software.yesaya.blog.ui.login

import software.yesaya.blog.data.repository.YesayaSoftwareRepository
import software.yesaya.blog.internal.lazyDeferred
import software.yesaya.blog.ui.base.YesayaSoftwareViewModel

class LoginViewModel(
    private val yesayaSoftwareRepository: YesayaSoftwareRepository
) : YesayaSoftwareViewModel(yesayaSoftwareRepository){
    val getLoginStatus by lazyDeferred {
        yesayaSoftwareRepository.token
    }
}