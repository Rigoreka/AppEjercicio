package com.example.ejercicios

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicios.databinding.ActivityNavegadorBinding

class Navegador : AppCompatActivity() {
    private lateinit var binding: ActivityNavegadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavegadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBusqueda.setOnClickListener {
            val url = binding.txtUrl.text.toString().trim() // Trim whitespace

            if (url.isNotEmpty()) {
                // Check if URL already includes "http://" or "https://"
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    val completeUrl = "https://" + url // Add "https://" prefix
                    val busqueda = Intent(Intent.ACTION_VIEW)
                    busqueda.setData(Uri.parse(completeUrl))
                    startActivity(busqueda)
                } else {
                    val busqueda = Intent(Intent.ACTION_VIEW)
                    busqueda.setData(Uri.parse(url)) // Use the entered URL directly
                    startActivity(busqueda)
                }
            } else {
                Toast.makeText(this, "Debe ingresar una URL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
