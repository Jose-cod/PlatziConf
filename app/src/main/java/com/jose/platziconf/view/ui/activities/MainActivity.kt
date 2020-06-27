package com.jose.platziconf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.firestore.FirebaseFirestore
import com.jose.platziconf.R
import com.jose.platziconf.model.Conference
import com.jose.platziconf.model.Speaker
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setData()
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.toolBarMain))
        configNav()
        //




    }
    fun configNav(){
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this,R.id.fragContent))
    }



}
