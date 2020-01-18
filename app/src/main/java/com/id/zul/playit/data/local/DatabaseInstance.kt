package com.id.zul.playit.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.id.zul.playit.model.favorite.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class DatabaseInstance : RoomDatabase() {

    abstract fun dataBaseObeject(): DatabaseObject

    companion object {

        var INSTANCE: DatabaseInstance? = null

        fun getDatabase(context: Context): DatabaseInstance {
            if (INSTANCE == null) {
                synchronized(Database::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        DatabaseInstance::class.java,
                        "FavoriteDB"
                    ).build()
                }
            }
            return INSTANCE as DatabaseInstance
        }
    }
}