package software.yesaya.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import software.yesaya.blog.ui.podcast.PodcastFragment

class PodcastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.podcast_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PodcastFragment.newInstance())
                .commitNow()
        }
    }

}
