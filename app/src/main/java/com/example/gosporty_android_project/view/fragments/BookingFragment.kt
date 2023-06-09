package com.example.gosporty_android_project.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gosporty_android_project.databinding.BookingFragmentBinding
import com.example.gosporty_android_project.view.PrefRepository
import com.example.gosporty_android_project.view.adapters.BookingsAdapter
import com.example.gosporty_android_project.view.models.Field
import com.example.gosporty_android_project.view.models.Reservation
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel
import com.example.gosporty_android_project.view.viewmodels.ReservationViewModel

class BookingFragment : Fragment() {

    private lateinit var  adapter: BookingsAdapter
    private val reservationViewModel: ReservationViewModel by viewModels()
    private val fieldViewModel: FieldViewModel by viewModels()
    private val reservations: ArrayList<Reservation>? = null
    val prefRepository: PrefRepository by lazy {
        PrefRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:BookingFragmentBinding = BookingFragmentBinding.inflate(inflater, container, false)
        adapter = BookingsAdapter()
        binding.fbBookingsRV.adapter = adapter
        binding.fbBookingsRV.layoutManager = LinearLayoutManager(this.context)
        binding.fbBookingsRV.setHasFixedSize(true)

        reservationViewModel.reservations.observe(viewLifecycleOwner){res ->
            for (booking in res){
                reservations?.add(Reservation(booking.id, booking.idEstablishment, booking.idField,booking.start, booking.end, booking.day, booking.month, booking.year, booking.owner))
            }
        }

        fieldViewModel.fields.observe(viewLifecycleOwner){field->

        }

        reservationViewModel.getSchedulesByUser(prefRepository.getUser().id!!)
        return binding.root
    }

    companion object{
        fun newInstance():BookingFragment{
            return BookingFragment()
        }
    }

}