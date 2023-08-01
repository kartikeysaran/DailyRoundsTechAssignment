package kartikey.dailyround.self.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import kartikey.dailyround.self.Repository
import kartikey.dailyround.self.UseCase
import kartikey.dailyround.self.db.UserRepository
import kartikey.dailyround.self.db.UserUseCase
import kartikey.dailyround.self.db.UserUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providesDataUseCase(beerRepository: Repository): UseCase {
        return UseCase(beerRepository)
    }

    @Provides
    @Singleton
    fun provideUsersUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCaseImpl(userRepository)
    }
}