package com.example.RoomCrud.Interface

import com.example.parcialCRUD.Contacto

interface AdaptadorListener {
    fun onEditItemClick(contacto: Contacto)
    fun onDeleteItemClick(contacto: Contacto)
}