package kartikey.dailyround.self.db

import javax.inject.Inject

interface UserRepository {

    fun addUser(users: UserEntity):Long

    fun addUserList(users: List<UserEntity>):List<Long>

    fun deleteUser(users: UserEntity)

    fun verifyLoginUser(mobNum:String,password:String): UserEntity

    fun getUserDataDetails(id:Long):UserEntity

}

class UserRepositoryImpl @Inject constructor(
    private  var usersDao: UserDao
):UserRepository{

    override fun addUser(users:UserEntity): Long {
        return usersDao.insertUser(users)
    }

    override fun addUserList(users:List<UserEntity>): List<Long> {
        return usersDao.insertUserAll(users)
    }

    override fun deleteUser(users: UserEntity) {
        //TODO("Not yet implemented")
    }

    override fun verifyLoginUser(email:String,password:String): UserEntity {
        return usersDao.readLoginData(email = email,password = password )
    }

    override fun getUserDataDetails(id:Long): UserEntity {
        return usersDao.getUserDataDetails(id)
    }
}