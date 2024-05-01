package com.example.ejercicios

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicios.databinding.ActivityLlamadaBinding
import com.example.ejercicios.databinding.ActivityMainBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class Llamada : AppCompatActivity() {

    private lateinit var binding: ActivityLlamadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLlamadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtBtnLlamada.setOnClickListener { requestPermissions() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun requestPermissions() {
        val phone = binding.txtPhone.text
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            when {
                ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    call()
                }

                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
            }
        }
    }

    private fun call() {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$")))
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){
            call()
        }
        else{
            Toast.makeText(this,"Necesitas activar los permisos",Toast.LENGTH_SHORT).show()
        }
    }
}


