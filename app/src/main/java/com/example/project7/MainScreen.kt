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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project7.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainScreen : Fragment() {
    private lateinit var binding: ActivityMainBinding
    var auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //creates the view and toolbar, including recycler view

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title="Notes"

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(MainActivity())
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { //creates the menu options for toolbar
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //makes things happen when toolbar icons are pressed
        when (item.itemId) {
            R.id.addNote -> {
                val fragment = NoteScreen()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav,fragment)?.commit()
                return true
            }

            R.id.logOut -> {
                auth.signOut()
                val fragment = loginScreen()
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
