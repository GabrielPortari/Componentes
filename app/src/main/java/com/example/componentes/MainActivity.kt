package com.example.componentes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Toast
import com.example.componentes.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        /* Toast */
        binding.buttonToast.setOnClickListener{
            val toast = Toast.makeText(this, "Toast", Toast.LENGTH_SHORT)
            toast.show()
        }

        /* Snackbar */
        binding.buttonSnack.setOnClickListener{
            val snackbar = Snackbar.make(binding.constraintLayout, "Snackbar", Snackbar.LENGTH_SHORT)

            snackbar.setTextColor(Color.CYAN)
            snackbar.setBackgroundTint(Color.BLACK)
            snackbar.setTextMaxLines(5)

            snackbar.setAction("Desfazer", View.OnClickListener {
                Snackbar.make(binding.constraintLayout, "Desfeito", Snackbar.LENGTH_SHORT).show()
            })
            snackbar.setActionTextColor(Color.MAGENTA)

            snackbar.show()
        }

        /* Carregar spinner dinamicamente */
        loadSpinner()

        /* Obter item spinner */
        binding.buttonGetSpinner.setOnClickListener{
            val selectedItemText = binding.spinnerDinamic.selectedItem.toString()
            val selectedItemId = binding.spinnerDinamic.selectedItemId.toString()
            val selectedItemPosition = binding.spinnerDinamic.selectedItemPosition.toString()
            val text =
                "Item: $selectedItemText\nId: $selectedItemId\nPosition: $selectedItemPosition"
            Snackbar.make(binding.constraintLayout, text, Snackbar.LENGTH_SHORT).show()
        }

        /* Setar item spinner */
        binding.buttonSetSpinner.setOnClickListener{
            binding.spinnerDinamic.setSelection(0)
        }

        /* Spinner event listener */
        binding.spinnerDinamic.onItemSelectedListener = this

        /* Seekbar event listeners */
        binding.seekBar.setOnSeekBarChangeListener(this)
        binding.seekBar.progress = 50

        /* SWITCH, CHECKBOX E RADIO BUTTONS UTILIZAM COMPOUND BUTTON COMO LISTENERS */
        /* Switch event listeners */
        binding.switch1.isChecked = true
        binding.switch1.text = "Switch ativado"
        binding.switch1.setOnCheckedChangeListener(this)

        /* Checkbox */
        binding.checkBox.isChecked = false
        binding.checkBox.setOnCheckedChangeListener(this)

        /* Radio buttons */
        binding.radioYes.isChecked = true
        binding.radioYes.setOnCheckedChangeListener(this)
        binding.radioNo.setOnCheckedChangeListener(this)

        /* Progress bar */
        binding.progressBar.visibility = ProgressBar.VISIBLE

        /* Floating action button chamando proxima activity */
        binding.floatingActionButton.setOnClickListener{
        startActivity(Intent(this, PickerActivity::class.java))
        }

    }

    private fun loadSpinner(){
        val spinnerItems = listOf("Selecione um item", "Dinamic item 1", "Dinamic item 2", "Dinamic item 3", "Dinamic item 4", "Dinamic item 5")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        binding.spinnerDinamic.adapter = spinnerAdapter
    }

    /* SPINNER INTERFACE */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "pos: $position, id: $id", Toast.LENGTH_SHORT).show()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "NOTHING", Toast.LENGTH_SHORT).show()
    }

    /* SEEKBAR INTERFACE */
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        binding.textSeekbar.text = "Progress: $progress -- User: $fromUser"
    }
    override fun onStartTrackingTouch(seekBar: SeekBar) {
        Toast.makeText(this, "Start Tracking", Toast.LENGTH_SHORT).show()
    }
    override fun onStopTrackingTouch(seekBar: SeekBar) {
        Toast.makeText(this, "Stop Tracking", Toast.LENGTH_SHORT).show()
    }

    /* SWITCH INTERFACE */
    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when(buttonView.id){
            R.id.switch1 -> {
                if(isChecked){
                    binding.switch1.text = "Switch ativado"
                }else{
                    binding.switch1.text = "Switch desativado"
                }
            }
            R.id.checkBox -> {
                Toast.makeText(this, "Active: $isChecked", Toast.LENGTH_SHORT).show()
            }
            R.id.radio_no -> {
                Toast.makeText(this, "'NO' button activated", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = ProgressBar.GONE
            }
            R.id.radio_yes -> {
                Toast.makeText(this, "'YES' button activated", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = ProgressBar.VISIBLE
            }
        }

    }
}