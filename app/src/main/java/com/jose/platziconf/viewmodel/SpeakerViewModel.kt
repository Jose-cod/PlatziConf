package com.jose.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jose.platziconf.model.Speaker
import com.jose.platziconf.network.Callback
import com.jose.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel: ViewModel() {
    val firestoreService= FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    lateinit var isLoading: MutableLiveData<Boolean>

    fun refresh(){
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase(){
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSucces(result: List<Speaker>?) {
                TODO("Not yet implemented")
                listSpeaker.postValue(result)
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
