package com.example.roomtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomtest.data.dao.BookDao
import com.example.roomtest.data.dao.UserDao
import com.example.roomtest.data.entity.Book
import com.example.roomtest.data.entity.User

@Database(entities = [User::class, Book::class], version = 3) // 多个实体类之间用逗号隔开即可
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao // 具体的方法实现是由Room在底层自动完成的

    abstract fun bookDao(): BookDao

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("create table Book (id integer primary key autoincrement not null, name text not null, pages integer not null)")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE Book ADD COLUMN author TEXT NOT NULL DEFAULT 'unknown'")
            }
        }

        @Volatile
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let { return it }
            return Room.databaseBuilder(
                context.applicationContext, // 使用applicationContext是为了避免内存泄漏
                AppDatabase::class.java,
                "app_database"
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build().apply {
                instance = this
            }
        }

    }

}