package com.example.parcialCRUD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class Contacto(
    @PrimaryKey var contacto: String,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "apellido") var apellido: String,
    @ColumnInfo(name = "numero") var numero: String,
    @ColumnInfo(name = "correo") var correo: String,
)