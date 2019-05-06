package software.yesaya.blog.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yesayasoftware.learning.data.db.dao.CategoryDao
import com.yesayasoftware.learning.data.db.dao.CommentDao
import com.yesayasoftware.learning.data.db.dao.PostDao
import com.yesayasoftware.learning.data.db.dao.UserDao
import software.yesaya.blog.data.db.entity.CategoryEntry
import software.yesaya.blog.data.db.entity.CommentEntry
import software.yesaya.blog.data.db.entity.PostEntry
import software.yesaya.blog.data.db.entity.UserEntry

@Database(
    entities = [UserEntry::class, CategoryEntry::class, PostEntry::class, CommentEntry::class],
    version = 1
)

@TypeConverters(LocalDateConverter::class)

abstract class YesayaSoftwareDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile private var instance: YesayaSoftwareDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                YesayaSoftwareDatabase::class.java, "yesayassoftware.db")
                .build()
    }
}