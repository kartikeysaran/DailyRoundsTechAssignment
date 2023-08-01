package kartikey.dailyround.self

import com.google.gson.JsonObject
import kartikey.dailyround.self.network.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun getCountries(): Flow<ResultData<JsonObject>> {
        return flow {
            emit(ResultData.Loading)

            val data = repository.getCountries()

            val resultData = if (data == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(data)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }
}