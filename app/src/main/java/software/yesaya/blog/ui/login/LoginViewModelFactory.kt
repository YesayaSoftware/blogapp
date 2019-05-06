package software.yesaya.blog.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import software.yesaya.blog.data.repository.YesayaSoftwareRepository

class LoginViewModelFactory(
    private val yesayaSoftwareRepository: YesayaSoftwareRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            yesayaSoftwareRepository
        ) as T
    }
}