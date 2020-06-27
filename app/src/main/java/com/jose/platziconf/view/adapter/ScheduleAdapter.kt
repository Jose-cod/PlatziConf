package com.jose.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jose.platziconf.R
import com.jose.platziconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener:ScheduleListener):RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    var listConference=ArrayList<Conference>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder =ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule,parent,false))

    override fun getItemCount()=listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference=listConference[position] as Conference

        holder.tvConferenceName.text=conference.title
        holder.tvConferenceSpeaker.text=conference.speaker
        holder.tvConferenceTag.text=conference.tag

        //seteando el formato de la hora
        val simpleDateFormat=SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM=SimpleDateFormat("a")

        val cal=Calendar.getInstance()
        val time=conference.datetime
        val hourFormat=simpleDateFormat.format(conference.datetime)

        holder.tvConferenceHour.text=hourFormat
        holder.tvConferenceAMPM.text=simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener{
            scheduleListener.onConferenceClicked(conference,position)
        }
    }

    fun updateData(data:List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvConferenceName=itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker=itemView.findViewById<TextView>(R.id.tvScheduleConferenceSpeaker)
        val tvConferenceTag=itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour=itemView.findViewById<TextView>(R.id.itemScheduleHour)
        val tvConferenceAMPM=itemView.findViewById<TextView>(R.id.itemScheduleHourAMPM)
    }

}