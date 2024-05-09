package id.rashio.android.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun bookmarkArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ArticleDatabase {
            if (INSTANCE == null) {
                synchronized(ArticleDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java, "article_database"
                    )
                        .build()
                }
            }
            return INSTANCE as ArticleDatabase
        }
    }
}