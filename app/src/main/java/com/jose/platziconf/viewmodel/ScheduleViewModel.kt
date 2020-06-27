package com.jose.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jose.platziconf.model.Conference
import com.jose.platziconf.network.Callback
import com.jose.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel(){
    val firestoreService=FirestoreService()
    var listSchedule:MutableLiveData<List<Conference>> = MutableLiveData()
    lateinit var isLoading:MutableLiveData<Boolean>

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object : Callback<List<Conference>>{
            override fun onSucces(result: List<Conference>?) {
                TODO("Not yet implemented")
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                TODO("Not yet implemented")
                processFinished()
            }

            fun processFinished(){
                isLoading.value=true
            }
        })
    }
}