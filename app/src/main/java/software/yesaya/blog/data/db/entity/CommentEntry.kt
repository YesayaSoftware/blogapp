package software.yesaya.blog.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import software.yesaya.blog.data.db.entity.PostEntry
import software.yesaya.blog.data.db.entity.UserEntry

@Entity(tableName = "comments")

data class CommentEntry(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val body: String,
    @Embedded(prefix = "post_")
    val post: PostEntry,
    @Embedded(prefix = "user_")
    val user: UserEntry,
    val created_at: String,
    val updated_at:  String? = null
)