package software.yesaya.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import software.yesaya.blog.ui.login.LoginFragment
import software.yesaya.blog.ui.post.PostFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}
