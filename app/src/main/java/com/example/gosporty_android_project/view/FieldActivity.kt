package com.example.gosporty_android_project.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gosporty_android_project.databinding.ActivityFieldBinding
import com.example.gosporty_android_project.view.adapters.TableRowAdapter
import com.example.gosporty_android_project.view.models.Reservation
import com.example.gosporty_android_project.view.models.TableRow
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel
import com.example.gosporty_android_project.view.viewmodels.ReservationViewModel
import java.util.Calendar

class FieldActivity : AppCompatActivity() {

    val fieldViewModel : FieldViewModel by viewModels()
    val reservationViewModel : ReservationViewModel by viewModels()
    val prefRepository by lazy {
        PrefRepository(this)
    }
    private lateinit var adapter: TableRowAdapter
    private lateinit var datePickerDialog : DatePickerDialog
    private var reservations = arrayListOf<Int>()

    val binding by lazy {
        ActivityFieldBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initReservations()

        adapter = TableRowAdapter()
        binding.fRowsRV.adapter = adapter
        binding.fRowsRV.layoutManager = LinearLayoutManager(this)
        binding.fRowsRV.setHasFixedSize(true)

        initDatePicker()
        binding.fDateBTN.setOnClickListener(){
            datePickerDialog.show()
        }

        binding.fBackIB.setOnClickListener {
            finish()
        }

        binding.fReserveBTN.setOnClickListener {
            //val reservations = adapter.getReservations()
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("fieldId", intent.getStringExtra("fieldId"))
            intent.putExtra("reservations", reservations)
            startActivity(intent)
        }

        fieldViewModel.field.observe(this) {
            val establishment = prefRepository.getEstablishment()
            binding.fSiteNameTV.text = establishment.name
            binding.fSiteNameCardTV.text = establishment.name
            binding.fAddressCardTV.text = establishment.address
            binding.fFieldNameTV.text = it.name
            binding.fDescriptionCardTV.text = it.name
            binding.fGradeCardTV.text = establishment.rating.toString()
        }

        reservationViewModel.reservations.observe(this) {
            Log.d("SCHEDULES", it.toString())
            removeReservated(it)
            adapter.fromIntToRow(reservations)
        }

        var id = intent.getStringExtra("fieldId")
        fieldViewModel.getField(id!!)
        reservationViewModel.getSchedules(prefRepository.getEstablishment().id!!,id!!)
    }

    fun initDatePicker(){
        val dateSetListener = DatePickerDialog.OnDateSetListener() { view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            binding.fDateBTN.text = "$dayOfMonth/${month+1}/$year"
        }

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(this, dateSetListener, year, month, dayOfMonth)
    }

    fun initReservations(){
        for (reservation in 0..23){
            reservations.add(reservation)
        }
    }

    fun removeReservated(reserveds : List<Reservation>){
        for (reserved in reserveds){
            for (toRemove in reserved.start until reserved.end){
                reservations.remove(toRemove)
            }
        }
    }
}