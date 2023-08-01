package kartikey.dailyround.self.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import kartikey.dailyround.self.Repository
import kartikey.dailyround.self.db.UserDao
import kartikey.dailyround.self.db.UserRepository
import kartikey.dailyround.self.db.UserRepositoryImpl
import kartikey.dailyround.self.network.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }

    @Provides
    fun provideUsersRepository(usersDao: UserDao): UserRepository {
        return UserRepositoryImpl(usersDao) as UserRepository
    }


}