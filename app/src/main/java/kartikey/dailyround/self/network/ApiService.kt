package kartikey.dailyround.self.network

import com.google.gson.JsonObject
import kartikey.dailyround.self.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.COUNTRY)
    suspend fun getCountries(): JsonObject?
}