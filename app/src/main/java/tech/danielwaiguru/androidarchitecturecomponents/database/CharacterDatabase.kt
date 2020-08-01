package tech.danielwaiguru.androidarchitecturecomponents.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import tech.danielwaiguru.androidarchitecturecomponents.CharacterDao
import tech.danielwaiguru.androidarchitecturecomponents.models.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    /**
     * Singleton Database instance
     */
    companion object{
        @Volatile
        var INSTANCE: CharacterDatabase? = null
        fun getDatabaseInstance(context: Context): CharacterDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDatabase::class.java,
                        "characters"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}