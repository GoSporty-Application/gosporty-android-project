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
        }
        holder.buttonIB.setOnClickListener{
            when(rows[position].button){
                "arrow" -> {
                    var intent = Intent(holder.context, FieldActivity::class.java)
                    intent.putExtra("field", rows[position].id)
                    holder.context.startActivity(intent)
                }
                "disabled" -> {
                    Toast.makeText(holder.context, "This field is already booked", Toast.LENGTH_SHORT).show()
                }
                "book" -> {
                    Toast.makeText(holder.context, "Booked", Toast.LENGTH_SHORT).show()
                }
                "unbook" -> {
                    Toast.makeText(holder.context, "Unbooked", Toast.LENGTH_SHORT).show()
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
}