package kartikey.dailyround.self.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    //for single user insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: UserEntity): Long

    //for list of users insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAll(users: List<UserEntity>): List<Long>

    //checking user exist or not in our db
    @Query("SELECT * FROM USERTABLE WHERE email LIKE :email AND password LIKE :password")
    fun readLoginData(email: String, password: String):UserEntity

    //getting user data details
    @Query("select * from usertable where id Like :id")
    fun getUserDataDetails(id:Long):UserEntity

    //deleting all user from db
    @Query("DELETE FROM USERTABLE")
    fun deleteAll()
}