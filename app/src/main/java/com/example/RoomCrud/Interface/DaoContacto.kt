package com.example.RoomCrud.Interface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parcialCRUD.Contacto

@Dao
interface DaoContacto {

    @Query("SELECT * FROM contactos")
    suspend fun obtenerContacto(): MutableList<Contacto>

    @Insert
    suspend fun agregarContacto(contacto: Contacto)

    @Query("UPDATE contactos SET nombre=:nombre, apellido=:apellido, numero=:numero, correo=:correo WHERE contacto=:contacto")
    suspend fun actualizarContacto(contacto: String, nombre: String, apellido: String, numero: String, correo: String)

    @Query("DELETE FROM contactos WHERE contacto=:contacto")
    suspend fun borrarContacto(contacto: String)

}