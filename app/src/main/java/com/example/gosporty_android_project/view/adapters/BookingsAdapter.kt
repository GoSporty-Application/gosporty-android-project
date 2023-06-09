package com.example.gosporty_android_project.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.view.models.Booking
import com.example.gosporty_android_project.view.models.Reservation
import com.example.gosporty_android_project.view.viewholders.BookingsViewHolder

class BookingsAdapter : Adapter<BookingsViewHolder>(){

    private var bookings:ArrayList<Booking> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val layoutInFlater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInFlater.inflate(R.layout.booking_card, parent, false)
        return BookingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        holder.typeText.text = bookings[position].type
        holder.siteText.text = bookings[position].site
        holder.fieldText.text = bookings[position].field
        holder.date.text = ""+bookings[position].day+"/"+
                bookings[position].month+"/"+
                bookings[position].year
        holder.hour.text = ""+bookings[position].start+
                ":00 - "+bookings[position].end+":00"
    }

    fun addBooking(booking: Booking) {
        var add:Boolean = true
        for (book in bookings){
            if(book.idReservation.equals(booking.idReservation)){
                add = false
            }
        }
        if(add){
            bookings.add(booking)
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return bookings.size
    }

}