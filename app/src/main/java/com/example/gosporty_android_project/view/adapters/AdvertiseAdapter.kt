package com.example.gosporty_android_project.view.adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.view.MapActivity
import com.example.gosporty_android_project.view.SiteActivity
import com.example.gosporty_android_project.view.models.Advertise
import com.example.gosporty_android_project.view.viewholders.AdvertiseViewHolder

class AdvertiseAdapter : Adapter<AdvertiseViewHolder>() {

    private var advertises:ArrayList<Advertise> = arrayListOf()

    init {
        advertises.add(Advertise(bgColor = "#0D2C54"))
        advertises.add(Advertise(bgColor = "#F7B500"))
        advertises.add(Advertise(bgColor = "#0D2C54"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertiseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.ad_card, parent, false)
        val holder = AdvertiseViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: AdvertiseViewHolder, position: Int) {
        holder.titleTV.text = advertises[position].title
        holder.fieldTV.text = advertises[position].field
        holder.discountTV.text = advertises[position].discount
        holder.cardCV.setCardBackgroundColor(Color.parseColor(advertises[position].bgColor))
        holder.cardCV.setOnClickListener{
            val intent: Intent = Intent(holder.context, SiteActivity::class.java)
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return advertises.size
    }

}