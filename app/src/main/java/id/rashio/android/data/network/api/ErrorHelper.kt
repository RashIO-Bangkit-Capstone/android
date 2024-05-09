package id.rashio.android.data.network.api

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Response

private val gson = Gson()

private fun getErrorBodyMessage(errorBody: ResponseBody?): String {
    val jsonObject = gson.fromJson(errorBody?.string(), JsonObject::class.java)
    return jsonObject.get("message").asString
}

// parse retrofit Response wrapper to Resource
fun <T> safeParseResponse(response: Response<T>): Result<T> =
    try {
        val body = response.body() // response body
        val responseCode = response.code()
        if (response.isSuccessful && body != null) {
            Result.success(body) // success fetching from network
        } else {
            val errorBody = response.errorBody()
            val message = getErrorBodyMessage(errorBody)
            Result.failure(
                Exception(message)
            ) // error fetching from network
        }
    } catch (exception: Throwable) {
        Result.failure(exception)
    }


