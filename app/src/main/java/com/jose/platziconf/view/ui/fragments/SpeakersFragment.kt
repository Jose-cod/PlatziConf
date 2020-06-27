package com.jose.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.jose.platziconf.R
import com.jose.platziconf.model.Conference
import com.jose.platziconf.model.Speaker
import com.jose.platziconf.view.adapter.ScheduleAdapter
import com.jose.platziconf.view.adapter.SpeakerAdapter
import com.jose.platziconf.view.adapter.SpeakerListener
import com.jose.platziconf.viewmodel.ScheduleViewModel
import com.jose.platziconf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewmodel: SpeakerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel= ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewmodel.refresh()

        speakerAdapter=SpeakerAdapter(this)
        rvSchedule.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter=speakerAdapter
        }

        observeViewModel()
    }
    fun observeViewModel(){
        viewmodel.listSpeaker.observe(viewLifecycleOwner, Observer<List<Speaker>>{ speaker->
            speakerAdapter.updateData(speaker)
        })

        viewmodel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it!=null)
                rlBase.visibility=View.INVISIBLE
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle= bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog,bundle)
    }

}
