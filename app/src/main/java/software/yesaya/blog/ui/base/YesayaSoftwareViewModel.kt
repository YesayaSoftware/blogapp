package software.yesaya.blog.ui.base

import androidx.lifecycle.ViewModel
import software.yesaya.blog.data.repository.YesayaSoftwareRepository

abstract class YesayaSoftwareViewModel(
    private val yesayaSoftwareRepository: YesayaSoftwareRepository
) : ViewModel()