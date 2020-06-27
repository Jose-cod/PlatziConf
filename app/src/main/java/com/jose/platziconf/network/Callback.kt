package com.jose.platziconf.network

import java.lang.Exception

interface Callback<T>{//Con la letra T se le dice que va a ser un tipo de dato distinto
    fun onSucces(result: T?)

    fun onFailed(exception: Exception)

}