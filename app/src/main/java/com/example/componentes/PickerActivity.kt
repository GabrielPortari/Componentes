package com.example.componentes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.componentes.databinding.ActivityMainBinding
import com.example.componentes.databinding.ActivityPickerBinding
import java.util.Calendar

class PickerActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        /* Timepicker dialog */
        binding.textTimePicker.text = "20 horas, 15 minutos"
        binding.buttonTimePicker.setOnClickListener{
            TimePickerDialog(this, this, 20, 15, true).show()
        }


        /* recuperando o dia atual */
        val calendar = Calendar.getInstance()
        val anoAtual = calendar.get(Calendar.YEAR)
        val mesAtual = calendar.get(Calendar.MONTH)
        val diaAtual = calendar.get(Calendar.DAY_OF_MONTH)

        /* Datepicker dialog */
        binding.buttonDatepicker.setOnClickListener{
            DatePickerDialog(this, this,  anoAtual, mesAtual, diaAtual).show()
        }
        binding.textDatePicker.text = "Dia $diaAtual, mes $mesAtual, ano $anoAtual"

    }

    /* Timepicker dialog interface*/
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        binding.textTimePicker.text = "$hourOfDay horas, $minute minutos"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        binding.textDatePicker.text = "Dia $dayOfMonth, mes $month, ano $year"
    }
}