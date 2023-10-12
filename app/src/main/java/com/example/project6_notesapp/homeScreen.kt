package com.example.project6_notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project6_notesapp.databinding.ActivityMainBinding


class homeScreen : Fragment() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        val addNote : Button = view.findViewById(R.id.addNote)
        addNote.setOnClickListener{
            val fragment = noteFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav,fragment)?.commit()
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(MainActivity())
        val notes = listOf(
            Note("Note 1"),
            Note("Another note!"),
            Note("Yet Another!")
        )
        recyclerView.adapter = NoteAdapter(notes)
        return view
    }
}