package com.example.project7

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore

class NoteScreen : Fragment() {
    var auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    private lateinit var database: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreateView( //creates view for this fragment
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_note, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title="Notes"
        val saveNote : Button = view.findViewById(R.id.noteSave)
        database = Firebase.database.reference
        var firestore =FirebaseFirestore.getInstance()
        var user = auth.currentUser
        saveNote.setOnClickListener{ //when saveNote is clicked, makes sure both title and description have strings then brings back to main page
            var title = view.findViewById<EditText>(R.id.noteTitle)
            var titleActual = title.text.toString().trim()
            var noteDescription = view.findViewById<EditText>(R.id.noteDescription)
            var noteActual = noteDescription.text.toString().trim()
            if (titleActual.isEmpty() && noteActual.isEmpty()){
                Toast.makeText(activity,"Title and Description Both Required", Toast.LENGTH_LONG).show()
            }
            else if (titleActual.isEmpty()){
                Toast.makeText(activity,"Title Required", Toast.LENGTH_LONG).show()
            }
            else if (noteActual.isEmpty()){
                Toast.makeText(activity,"Description Required", Toast.LENGTH_LONG).show()
            }
            else{

                val fragment = MainScreen()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav,fragment)?.commit()
            }
        }
        return view
    }
    fun showDeleteConfirmationDialog() { //shows a confirmation when delete button is pressed
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Confirm Delete")
        alertDialogBuilder.setMessage("Are you sure you want to delete this note?")

        alertDialogBuilder.setPositiveButton("Delete") { dialog, which ->
        }

        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->

        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { //creates the menu options display
        inflater.inflate(R.menu.menunote, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //creates actions when menu icons are pressed
        when (item.itemId) {
            R.id.deleteNote -> {
                showDeleteConfirmationDialog()
                val fragment = MainScreen()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav,fragment)?.commit()
                return true
            }

            R.id.back -> {
                val fragment = MainScreen()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav,fragment)?.commit()
                return true
            }
            else -> {
                return true
            }
        }
    }
}