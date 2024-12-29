package com.example.note_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.note_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var database: NoteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)

        database= Room.databaseBuilder(requireActivity(),NoteDatabase::class.java,"Note-DB")
            .allowMainThreadQueries().build()

        var notes:List<Note> =database.getNoteDao().getAllData()
        var adapter=NoteAdapter()
        adapter.submitList(notes)
        binding.RecyclerView.adapter=adapter

        database.getNoteDao().getAllData().forEach {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }

       binding.AddBtn.setOnClickListener {
           findNavController().navigate(R.id.action_homeFragment_to_addFragment)
       }



        return binding.root
    }

}