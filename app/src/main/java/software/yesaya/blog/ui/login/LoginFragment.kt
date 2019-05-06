package software.yesaya.blog.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import software.yesaya.blog.R
import software.yesaya.blog.ui.base.ScopedFragment
import software.yesaya.blog.ui.post.PostFragment

class LoginFragment : ScopedFragment(), KodeinAware {
    companion object {
        fun newInstance() = PostFragment()
    }

    override val kodein by closestKodein()
    private val viewModelFactory : LoginViewModelFactory by instance()

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val loginStatus = viewModel.getLoginStatus.await()

        loginStatus.accessToken
    }
}
