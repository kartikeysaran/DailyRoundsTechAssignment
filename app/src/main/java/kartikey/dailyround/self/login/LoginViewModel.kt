package kartikey.dailyround.self.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kartikey.dailyround.self.db.UserEntity
import kartikey.dailyround.self.db.UserUseCase
import kartikey.dailyround.self.network.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var userUseCase: UserUseCase): ViewModel() {
    private val _GetUserLoginDataStatus = MutableLiveData<ResultData<UserEntity>>()

    val getUserLoginDataStatus: MutableLiveData<ResultData<UserEntity>> = _GetUserLoginDataStatus

    private val _GetUserProfileDataStatus = MutableLiveData<ResultData<UserEntity>>()

    val getUserProfileDataStatus: MutableLiveData<ResultData<UserEntity>> = _GetUserProfileDataStatus

    fun getUserLoginDataStatus(email:String,password:String) {
        viewModelScope.launch {
            _GetUserLoginDataStatus.postValue(ResultData.Loading)
            try {
                val data = userUseCase.getUserLoginVerify(email,password)
                _GetUserLoginDataStatus.postValue(ResultData.Success(data))
            } catch (exception: Exception) {
                _GetUserLoginDataStatus.postValue(ResultData.Failed(exception.message!!))
            }
        }
    }

    fun getUserProfileData(id:Long){
        viewModelScope.launch {
            _GetUserProfileDataStatus.postValue(ResultData.Loading)
            try {
                val data = userUseCase.getUserData(id)
                _GetUserProfileDataStatus.postValue(ResultData.Success(data))
            } catch (exception: Exception) {
                _GetUserProfileDataStatus.postValue(ResultData.Failed(exception.message!!))
            }
        }
    }
}