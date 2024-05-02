package id.rashio.android.data.network.api

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkCallAdapter(private val type: Type) : CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType(): Type = type

    override fun adapt(p0: Call<Type>): Call<Result<Type>> = NetworkCallResult(p0)
}