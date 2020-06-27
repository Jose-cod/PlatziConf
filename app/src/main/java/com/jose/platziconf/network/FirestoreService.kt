package com.jose.platziconf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.jose.platziconf.model.Conference
import com.jose.platziconf.model.Speaker

const val CONFERENCES_COLLECTION_NAME="conferences"
const val SPEAKER_COLLECTION_NAME="speakers"

class FirestoreService {
    val firebaseFirestore=FirebaseFirestore.getInstance()
    val settings=FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    //inicializador (es parecido a un constructor)
    init {
        firebaseFirestore.firestoreSettings=settings
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKER_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result->
                for(doc in result){
                    val list=result.toObjects(Speaker::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }

    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result->
                for(doc in result){
                    val list=result.toObjects(Conference::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }
}