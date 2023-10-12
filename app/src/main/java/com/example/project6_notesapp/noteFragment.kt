package com.example.project6_notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class noteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_note, container, false)
        val saveNote : Button = view.findViewById(R.id.noteSave)
        saveNote.setOnClickListener{
            val fragment = homeScreen()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav,fragment)?.commit()
        }
        return view
    }

}