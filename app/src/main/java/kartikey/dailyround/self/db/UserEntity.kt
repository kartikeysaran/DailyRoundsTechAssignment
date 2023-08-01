package kartikey.dailyround.self.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kartikey.dailyround.self.Constants

@Entity(tableName = Constants.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    var email:String?="",
    var password: String?="",
    var country:String?=""
)