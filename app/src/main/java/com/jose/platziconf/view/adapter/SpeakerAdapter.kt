package com.jose.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jose.platziconf.R
import com.jose.platziconf.model.Conference
import com.jose.platziconf.model.Speaker

class SpeakerAdapter(val speakerListener:SpeakerListener):RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){
    val listSpeaker=ArrayList<Speaker>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerAdapter.ViewHolder=ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))

    override fun getItemCount()=listSpeaker.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker=listSpeaker[position]
        holder.tvSpeakerName.text=speaker.name
        holder.tvSpeakerJob.text=speaker.jobtitle
        Glide.with(holder.itemView.context).load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.tvSpeakerPhoto)

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClicked(speaker,position)
        }
    }
    fun updateData(data:List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvSpeakerName=itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerPhoto=itemView.findViewById<ImageView>(R.id.ivItemSpeakerPhoto)
        val tvSpeakerJob=itemView.findViewById<TextView>(R.id.tvItemSpeakerJob)
    }
}