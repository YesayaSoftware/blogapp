package software.yesaya.blog

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.yesayasoftware.learning.data.network.ConnectivityInterceptor
import com.yesayasoftware.learning.data.network.ConnectivityInterceptorImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import software.yesaya.blog.data.db.YesayaSoftwareDatabase

class YesayaSoftwareApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@YesayaSoftwareApplication))

        bind() from singleton { YesayaSoftwareDatabase(instance()) }

        bind() from singleton { instance<YesayaSoftwareDatabase>().userDao() }
        bind() from singleton { instance<YesayaSoftwareDatabase>().categoryDao() }
        bind() from singleton { instance<YesayaSoftwareDatabase>().postDao() }
        bind() from singleton { instance<YesayaSoftwareDatabase>().commentDao() }

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}