package com.example.first_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.text.isDigitsOnly
import com.example.first_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
        setupTextWatcher()
    }

    private lateinit var binding: ActivityMainBinding

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnStart.setOnClickListener {
            registerCommandsFun()
        }
        binding.btnShow.setOnClickListener {
            val intent = Intent(this, WinnersScreen::class.java)
            startActivity(intent)
        }
    }

    private fun registerCommandsFun() {
        val nameA = binding.editText1.text.toString()
        val nameB = binding.editText2.text.toString()
        val time = binding.editText3.text.toString().toInt()
        GameScoreActivity.start(this, nameA, nameB, time)
        binding.editText1.text = null
        binding.editText2.text = null
        binding.editText3.text = null
        binding.btnStart.isEnabled = false
    }

    private fun setupTextWatcher() {
        setupError()
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val firstInput = binding.editText1.text.toString()
                val secondInput = binding.editText2.text.toString()
                val thirdInput = binding.editText3.text.toString()
                binding.btnStart.isEnabled =
                    firstInput.isNotEmpty() && secondInput.isNotEmpty() && thirdInput.isNotEmpty() && thirdInput.isDigitsOnly()
            }
        }
        addTextListener(textWatcher)
    }

    private fun setupError() {
        binding.editText1.error = "Enter Team A name"
        binding.editText2.error = "Enter Team B name"
        binding.editText3.error = "Enter time in seconds"
    }

    private fun addTextListener(textWatcher: TextWatcher) {
        binding.editText1.addTextChangedListener(textWatcher)
        binding.editText2.addTextChangedListener(textWatcher)
        binding.editText3.addTextChangedListener(textWatcher)
    }
}