package com.example.gosporty_android_project.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gosporty_android_project.R
import com.example.gosporty_android_project.view.FieldActivity
import com.example.gosporty_android_project.view.models.TableRow
import com.example.gosporty_android_project.view.viewholders.TableRowViewHolder

class TableRowAdapter : RecyclerView.Adapter<TableRowViewHolder>() {

    private var rows:ArrayList<TableRow> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableRowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.table_row, parent, false)
        val holder = TableRowViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: TableRowViewHolder, position: Int) {
        holder.principalTV.text = rows[position].principal
        holder.secondaryTV.text = rows[position].secondary
        when(rows[position].button){
            "disabled" -> {
                holder.buttonIB.isEnabled = false
                holder.buttonIB.setImageResource(R.drawable.booked_by_other)
            }
            "book" -> {
                holder.buttonIB.isEnabled = true
                holder.buttonIB.setImageResource(R.drawable.check)
            }
            "unbook" -> {
                holder.buttonIB.isEnabled = true
                holder.buttonIB.setImageResource(R.drawable.booked)
            }
        }
        holder.buttonIB.setOnClickListener{
            when(rows[position].button){
                "arrow" -> {
                    var intent = Intent(holder.context, FieldActivity::class.java)
                    intent.putExtra("fieldId", rows[position].id)
                    holder.context.startActivity(intent)
                }
                "disabled" -> {
                    Toast.makeText(holder.context, "This field is already booked", Toast.LENGTH_SHORT).show()
                }
                "book" -> {
                    rows[position].button = "unbook"
                    notifyDataSetChanged()
                }
                "unbook" -> {
                    rows[position].button = "book"
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    fun addRow(tableRow: TableRow) {
        rows.add(tableRow)
        notifyDataSetChanged()
    }

    fun getReservations(): ArrayList<Int> {
        var reservations: ArrayList<Int> = arrayListOf()
        for (row in rows){
            if (row.button == "unbook"){
                var initSplit = row.principal.split("-")[0]
                initSplit.trim()
                var init = initSplit.split(":")[0]
                reservations.add(init.toInt())
            }
        }
        return reservations
    }

    fun fromIntToRow(reservations: List<Int>){
        var newRows = arrayListOf<TableRow>()
        for (row in 0..23){
            if (reservations.contains(row)){
                newRows.add(TableRow("", "${row}:00 - ${row+1}:00", "", "book"))
            } else {
                newRows.add(TableRow("", "${row}:00 - ${row+1}:00", "", "disabled"))
            }
        }
        rows = newRows
        notifyDataSetChanged()
    }
}