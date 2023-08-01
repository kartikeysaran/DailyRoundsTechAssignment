package kartikey.dailyround.self

import com.google.gson.JsonObject
import kartikey.dailyround.self.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCountries(): JsonObject? {
        return apiService.getCountries()
    }
}