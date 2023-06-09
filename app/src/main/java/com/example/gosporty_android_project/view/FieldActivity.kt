package com.example.gosporty_android_project.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gosporty_android_project.databinding.ActivityFieldBinding
import com.example.gosporty_android_project.view.adapters.TableRowAdapter
import com.example.gosporty_android_project.view.models.Field
import com.example.gosporty_android_project.view.models.Reservation
import com.example.gosporty_android_project.view.models.TableRow
import com.example.gosporty_android_project.view.viewmodels.FieldViewModel
import com.example.gosporty_android_project.view.viewmodels.ReservationViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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
    private var day : Int = 0
    private var month : Int = 0
    private var year : Int = 0
    private var fieldId : String = ""

    val binding by lazy {
        ActivityFieldBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("fieldId", fieldId)
            intent.putExtra("reservations", adapter.getReservations())
            intent.putExtra("day", day)
            intent.putExtra("month", month)
            intent.putExtra("year", year)
            startActivity(intent)
            finish()
        }

        fieldViewModel.field.observe(this) {
            val establishment = prefRepository.getEstablishment()
            binding.fSiteNameTV.text = establishment.name
            binding.fSiteNameCardTV.text = establishment.name
            binding.fAddressCardTV.text = establishment.address
            binding.fFieldNameTV.text = it.name
            binding.fDescriptionCardTV.text = it.name
            binding.fGradeCardTV.text = establishment.rating.toString()
            downloadImages(it.photo , establishment.logo, it.id, establishment.id)
        }

        reservationViewModel.reservations.observe(this) {
            Log.d("SCHEDULES", it.toString())
            initReservations()
            removeReservated(it)
            adapter.fromIntToRow(reservations)
        }

        fieldId = intent.getStringExtra("fieldId")!!
        fieldViewModel.getField(fieldId)
    }

    fun initDatePicker(){
        val dateSetListener = DatePickerDialog.OnDateSetListener() { view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            this.day = dayOfMonth
            this.month = month+1
            this.year = year
            binding.fDateBTN.text = "$dayOfMonth/${month+1}/$year"
            var id = intent.getStringExtra("fieldId")
            reservationViewModel.getSchedules(prefRepository.getEstablishment().id!!,id!!,dayOfMonth,month+1,year)
        }

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(this, dateSetListener, year, month, dayOfMonth)
    }

    fun initReservations(){
        reservations.clear()
        for (reservation in 0..23){
            reservations.add(reservation)
        }
    }

    private fun downloadImages(photoID: String?, logoID: String?, fieldID: String?, siteID: String?){
        Log.e(">>>>>>>", ("PHOTO: "+photoID+" LOGO: "+logoID+" FieldID: "+fieldID +" SiteID "+siteID))
            Firebase.storage.getReference().child("fields").child(fieldID!!).child(photoID!!+".jpg").downloadUrl.addOnSuccessListener {
                Glide.with(binding.sSitePhotoIV).load(it).into(binding.sSitePhotoIV)
            }
        Firebase.storage.getReference().child("establishments").child(siteID!!).child(logoID!!+".png").downloadUrl.addOnSuccessListener {
            Glide.with(binding.fSiteLogoIV).load(it).into(binding.fSiteLogoIV)
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