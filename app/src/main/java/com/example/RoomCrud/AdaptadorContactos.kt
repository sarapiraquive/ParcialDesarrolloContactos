package com.example.parcialCRUD

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.RoomCrud.Interface.AdaptadorListener
import com.example.crud_room_kotlin.R

class AdaptadorContactos(
    val listaContactos: MutableList<Contacto>,
    val listener: AdaptadorListener
): RecyclerView.Adapter<AdaptadorContactos.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_contacto, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = listaContactos[position]

        holder.tvContacto.text = contacto.contacto
        holder.tvNombre.text = contacto.nombre

        holder.cvContacto.setOnClickListener {
            listener.onEditItemClick(contacto)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(contacto)
        }
    }

    override fun getItemCount(): Int {
        return listaContactos.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvContacto = itemView.findViewById<CardView>(R.id.cvContacto)
        val tvContacto = itemView.findViewById<TextView>(R.id.tvNombre)
        val tvNombre= itemView.findViewById<TextView>(R.id.tvApellido)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
    }

}