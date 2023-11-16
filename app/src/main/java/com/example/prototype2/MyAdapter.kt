package com.example.prototype2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val Appointment: List<String>, val context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Appointment.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNumberValue!!.text = "List #" + (position + 1)
        holder.bindTextValue!!.text = Appointment[position]

        holder.itemView.setOnClickListener {
            // Start the new activity with the value from the clicked row
            val intent = Intent(context, ChangeAppointmentDateActivity::class.java)
            intent.putExtra("toBeChangeDate", Appointment[position])
            context.startActivity(intent)
        }
    }

}

class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
    val bindNumberValue: TextView? = itemview.findViewById(R.id.appointmentNumberText)
    val bindTextValue: TextView? = itemview.findViewById(R.id.appointmentTimeText)
}
