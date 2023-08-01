package kartikey.dailyround.self.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kartikey.dailyround.self.UseCase
import kartikey.dailyround.self.db.UserEntity
import kartikey.dailyround.self.db.UserUseCase
import kartikey.dailyround.self.network.ResultData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val useCase: UseCase, private var userUseCase: UserUseCase): ViewModel() {
    private val _data : MutableLiveData<ResultData<JsonObject>> = MutableLiveData()
    val data: LiveData<ResultData<JsonObject>>
        get() = _data

    fun getCountries() {
        viewModelScope.launch {
            useCase.getCountries().onEach {
                _data.postValue(it)
            }.collect()
        }
    }

    private val _insertUsersDataStatus = MutableLiveData<ResultData<Long>>()

    val insertUsersDataStatus: LiveData<ResultData<Long>> = _insertUsersDataStatus

    private val _insertUsersDataStatusList = MutableLiveData<ResultData<List<Long>>>()

    val insertUsersDataStatusList: LiveData<ResultData<Long>> = _insertUsersDataStatus

    fun insertUserData(users: UserEntity) {
        viewModelScope.launch {
            _insertUsersDataStatus.postValue(ResultData.Loading)
            try {
                val data = userUseCase.addUser(users)
                _insertUsersDataStatus.postValue(ResultData.Success(data))
            } catch (exception: Exception) {
                _insertUsersDataStatus.postValue(ResultData.Failed(exception.message!!))
            }
        }
    }
}