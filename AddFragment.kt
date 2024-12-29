package com.example.note_app

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.note_app.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
  var showDate:String?=null
    var showTime:String?=null
    lateinit var database: NoteDatabase
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddBinding.inflate(inflater,container,false)
  database=Room.databaseBuilder(requireActivity(),NoteDatabase::class.java,"Note-DB")
      .allowMainThreadQueries().build()
     binding.DateBtn.setOnClickListener {
        PicADate()
 }
        binding.TimeBtn.setOnClickListener {
            PicATime()
        }
        binding.submitBtn.setOnClickListener {
            val titleStr= binding.tittleET.text.toString()
            val timerStr=showTime ?:"00:00"
            val dateStr=showDate?:"00/00/0000"

            val note=Note(title = titleStr, time = titleStr, date = dateStr)
            database.getNoteDao().insertData(note)
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }




        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun PicATime() {
        val calendar= Calendar.getInstance()
        val minute=calendar.get(Calendar.MINUTE)
        val hour =calendar.get(Calendar.HOUR_OF_DAY)
        val ShowTime= TimePickerDialog(requireActivity(),TimePickerDialog.OnTimeSetListener{view,hourOfDay,minute->
             showTime = "$hour:$minute"
            binding.TimeBtn.text=showTime
        },hour,minute,false)
        ShowTime.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun PicADate() {
        val calender = Calendar.getInstance()
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val showDatePicker = DatePickerDialog(requireActivity(),DatePickerDialog.OnDateSetListener{view,Year,month,dayOfMonth->
             showDate = "$year/${month + 1}/$day"
            binding.DateBtn.text=showDate
        },year,month,day)
        showDatePicker.show()
    }

}