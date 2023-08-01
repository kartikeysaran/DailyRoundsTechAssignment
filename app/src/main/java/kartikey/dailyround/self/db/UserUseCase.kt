package kartikey.dailyround.self.db

import dagger.Provides
import javax.inject.Inject

interface UserUseCase {
    suspend fun addUser(users: UserEntity): Long
    suspend fun addUserList(users: List<UserEntity>): List<Long>
    suspend fun getUserLoginVerify(email: String, password: String): UserEntity
    suspend fun getUserData(id:Long): UserEntity

}

class UserUseCaseImpl @Inject constructor(private var userRepository: UserRepository):UserUseCase{
    override suspend fun addUser(users: UserEntity): Long {
        val id= userRepository.addUser(users)
        return id
    }

    override suspend fun addUserList(users: List<UserEntity>): List<Long> {
        val id= userRepository.addUserList(users)
        return id
    }

    override suspend fun getUserLoginVerify(email: String, password: String): UserEntity {
        return userRepository.verifyLoginUser(email, password)
    }

    override suspend fun getUserData(id: Long): UserEntity {
        return userRepository.getUserDataDetails(id)
    }

}