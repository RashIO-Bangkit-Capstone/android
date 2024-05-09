package id.rashio.android.data.network.api

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkCallResult<T>(private val call: Call<T>) : Call<Result<T>> {
    override fun clone(): Call<Result<T>> = NetworkCallResult(call.clone())

    override fun execute(): Response<Result<T>> = throw NotImplementedError()

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun timeout(): Timeout = call.timeout()

    override fun request(): Request = call.request()

    override fun enqueue(callback: Callback<Result<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(p0: Call<T>, p1: Response<T>) {
                val result = safeParseResponse(p1)
                callback.onResponse(this@NetworkCallResult, Response.success(result))
            }

            override fun onFailure(p0: Call<T>, p1: Throwable) {
                val result = Result.failure<T>(p1)
                callback.onResponse(this@NetworkCallResult, Response.success(result))
            }

        })
    }

}