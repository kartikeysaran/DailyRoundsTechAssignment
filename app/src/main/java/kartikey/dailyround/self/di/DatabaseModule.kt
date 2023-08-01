package kartikey.dailyround.self.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kartikey.dailyround.self.Constants
import kartikey.dailyround.self.db.AppDatabase
import kartikey.dailyround.self.db.UserDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        Constants.DB_NAME
    ).allowMainThreadQueries().build()

    @Provides
    internal fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao
    }
 }