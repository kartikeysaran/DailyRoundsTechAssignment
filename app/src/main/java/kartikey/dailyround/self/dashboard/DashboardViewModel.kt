package kartikey.dailyround.self.dashboard

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
class DashboardViewModel @Inject constructor(private var userUseCase: UserUseCase): ViewModel() {

    private val _GetUserDataDetails = MutableLiveData<ResultData<UserEntity>>()

    val getUserDataDetails: MutableLiveData<ResultData<UserEntity>> = _GetUserDataDetails

    fun getUserDataDetails(id:Long) {
        viewModelScope.launch {
            _GetUserDataDetails.postValue(ResultData.Loading)
            try {
                val data = userUseCase.getUserData(id)
                _GetUserDataDetails.postValue(ResultData.Success(data))
            } catch (exception: Exception) {
                _GetUserDataDetails.postValue(ResultData.Failed(exception.message!!))
            }
        }
    }
}