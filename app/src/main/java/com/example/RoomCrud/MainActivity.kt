package com.example.parcialCRUD

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.RoomCrud.Interface.AdaptadorListener
import com.example.crud_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding

    var listaContactos: MutableList<Contacto> = mutableListOf()

    lateinit var adatador: AdaptadorContactos

    lateinit var room: DBPrueba

    lateinit var contacto: Contacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContactos.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBPrueba::class.java, "dbPruebas").build()

        obtenerContacto(room)

        binding.btnAddUpdate.setOnClickListener {
            if(binding.etNombre.text.isNullOrEmpty() || binding.etApellido.text.isNullOrEmpty() || binding.etNumero.text.isNullOrEmpty() || binding.etCorreo.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("agregar")) {
                contacto = Contacto(
                    binding.etNombre.text.toString().trim(),
                    binding.etApellido.text.toString().trim(),
                    binding.etNumero.text.toString().trim(),
                    binding.etCorreo.text.toString().trim(),
                    binding.etCorreo.text.toString().trim(),
                )

                agregarContacto(room, contacto)
            } else if(binding.btnAddUpdate.text.equals("actualizar")) {
                contacto.nombre = binding.etApellido.text.toString().trim()

                actualizarContacto(room, contacto)
            }
        }

    }

    fun obtenerContacto(room: DBPrueba) {
        lifecycleScope.launch {
            listaContactos = room.daoContacto().obtenerContacto()
            adatador = AdaptadorContactos(listaContactos, this@MainActivity)
            binding.rvContactos.adapter = adatador
        }
    }

    fun agregarContacto(room: DBPrueba, contacto: Contacto) {
        lifecycleScope.launch {
            room.daoContacto().agregarContacto(contacto)
            obtenerContacto(room)
            limpiarCampos()
        }
    }

    fun actualizarContacto(room: DBPrueba, contacto: Contacto) {
        lifecycleScope.launch {
            room.daoContacto().actualizarContacto(contacto.contacto, contacto.nombre, contacto.apellido, contacto.numero, contacto.correo)
            obtenerContacto(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        contacto.nombre = ""
        contacto.apellido = ""
        contacto.numero = ""
        contacto.correo = ""

        binding.etNombre.setText("")
        binding.etApellido.setText("")
        binding.etNumero.setText("")
        binding.etCorreo.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etNombre.isEnabled = true
            binding.etApellido.isEnabled = true
            binding.etNumero.isEnabled = true
            binding.etCorreo.isEnabled = true
        }

    }

    override fun onEditItemClick(contacto: Contacto) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etNombre.isEnabled = false
        this.contacto = contacto
        binding.etNombre.setText(this.contacto.nombre)
        binding.etApellido.setText(this.contacto.apellido)
        binding.etNumero.setText(this.contacto.numero)
        binding.etCorreo.setText(this.contacto.correo)
    }

    override fun onDeleteItemClick(contacto: Contacto) {
        lifecycleScope.launch {
            room.daoContacto().borrarContacto(contacto.nombre)
            adatador.notifyDataSetChanged()
            obtenerContacto(room)
        }
    }
}