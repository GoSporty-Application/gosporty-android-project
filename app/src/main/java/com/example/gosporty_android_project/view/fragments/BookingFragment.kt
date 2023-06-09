package com.example.gosporty_android_project.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gosporty_android_project.databinding.BookingFragmentBinding
import com.example.gosporty_android_project.view.PrefRepository
import com.example.gosporty_android_project.view.adapters.BookingsAdapter
import com.example.gosporty_android_project.view.models.Booking
import com.example.gosporty_android_project.view.models.Establishment
import com.example.gosporty_android_project.view.models.Field
import com.example.gosporty_android_project.view.models.Reservation
import com.example.gosporty_android_project.view.viewmodels.EstablishmentViewModel
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel
import com.example.gosporty_android_project.view.viewmodels.ReservationViewModel

class BookingFragment : Fragment() {

    private lateinit var  adapter: BookingsAdapter
    private val reservationViewModel: ReservationViewModel by viewModels()
    private val fieldViewModel: FieldViewModel by viewModels()
    private val establishmentViewModel: EstablishmentViewModel by viewModels()
    private val reservations = arrayListOf<Reservation>()
    private val establishments = arrayListOf<Establishment>()
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
                reservations.add(booking)
                establishmentViewModel.getEstablishment(booking.idEstablishment)
            }
        }

        establishmentViewModel.establishment.observe(viewLifecycleOwner){est->
            for (reservation in reservations){
                if (est.id.equals(reservation.idEstablishment)){
                    establishments.add(est)
                    fieldViewModel.getField(reservation.idField)
                }
                break
            }
        }

        fieldViewModel.field.observe(viewLifecycleOwner){field->
            for (reservation in reservations){
                if (field.id.equals(reservation.idField)){
                    for (establishment in establishments){
                        if (establishment.id.equals(reservation.idEstablishment)){
                            var typeField: String = field.name.split(" ")[0]
                            var reserve:Booking = Booking(
                                typeField,
                                establishment.name,
                                field.name,
                                reservation.day,
                                reservation.month,
                                reservation.year,
                                reservation.start,
                                reservation.end,
                                field.photo,
                                establishment.id!!,
                                field.id,
                                reservation.id!!
                            )
                            adapter.addBooking(reserve)
                            Log.d("BookingFragment","Se anadio una reserva: $reserve")
                            break
                        }
                    }
                }
            }
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