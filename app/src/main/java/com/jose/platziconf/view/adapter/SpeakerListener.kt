package com.jose.platziconf.view.adapter

import com.jose.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position:Int)
}