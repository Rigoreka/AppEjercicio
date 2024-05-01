package com.example.ejercicios

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicios.databinding.ActivityMainBinding
import com.example.ejercicios.databinding.ActivityMensajeBienvenidaBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.textView.setOnClickListener{
            val mensaje= "Hola bienvenido"
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
        }
        binding.btnActividad.setOnClickListener{
            val intent= Intent(this,MensajeBienvenida::class.java)
            startActivity(intent)
        }
        binding.btnCompartir.setOnClickListener{

            val textoTextView = binding.textView.text.toString()

            // Crear un Intent implícito para compartir el contenido del TextView
            val intentCompartir = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, textoTextView)
            }

            // Mostrar al usuario una lista de aplicaciones disponibles para compartir el mensaje
            startActivity(Intent.createChooser(intentCompartir, "Compartir mensaje"))
        }
        binding.btnLlamada.setOnClickListener {
            val intent= Intent(this,Llamada::class.java)
            startActivity(intent)
        }
        binding.btnNavegador.setOnClickListener {
            val intent=Intent(this,Navegador::class.java)
            startActivity(intent)
        }

    }
}