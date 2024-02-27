package com.example.parcialCRUD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.RoomCrud.Interface.DaoContacto

@Database(
    entities = [Contacto::class],
    version = 2
)
abstract class DBPrueba: RoomDatabase() {
    abstract fun daoContacto(): DaoContacto
}