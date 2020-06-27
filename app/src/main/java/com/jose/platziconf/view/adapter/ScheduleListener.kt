package com.jose.platziconf.view.adapter

import com.jose.platziconf.model.Conference

interface ScheduleListener{
    fun onConferenceClicked(conference: Conference, position:Int)
}