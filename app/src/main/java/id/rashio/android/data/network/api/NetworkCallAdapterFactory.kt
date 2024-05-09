package id.rashio.android.data.network.api

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        fun create() = NetworkCallAdapterFactory()
    }

    override fun get(p0: Type, p1: Array<out Annotation>, p2: Retrofit): CallAdapter<*, *>? {
        if (getRawType(p0) != Call::class.java) return null
        val callType = getParameterUpperBound(0, p0 as ParameterizedType)
        if (getRawType(callType) != Result::class.java) return null
        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkCallAdapter(resultType)
    }
}