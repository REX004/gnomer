package com.example.testchamp.session2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testchamp.R
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.launch


class LogIn : Fragment() {


    private lateinit var supabase: SupabaseClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bF1 = view.findViewById<TextView>(R.id.signUp3)
        val bF2 = view.findViewById<TextView>(R.id.signIm3)

        val controller = findNavController()
//        bF1.setOnClickListener { controller.navigate(R.id.signIm) }
        bF2.setOnClickListener { controller.navigate(R.id.signUp) }
        val supabaseUrl = "https://tnmahbgcomhugvcalfgk.supabase.co"
        val supabaseKey =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRubWFoYmdjb21odWd2Y2FsZmdrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDY3NzgwNjAsImV4cCI6MjAyMjM1NDA2MH0.6s6CPbw4ibYEtBP2FBOlf8JFA7R5dpqzJ6284ULBF0I"

        supabase = createSupabaseClient(supabaseUrl = supabaseUrl, supabaseKey = supabaseKey) {
            install(Auth)
            install(Realtime)
            install(Storage)
            install(Postgrest)
        }
        val email_get = view.findViewById<EditText>(R.id.nameET8)

        bF1.setOnClickListener {
            lifecycleScope.launch {
                val enteredEmail = email_get.text.toString()

                val user = supabase.auth.signInWith(Email) {
                    email = enteredEmail
                    password = "example-password"
                }
            }
        }
    }
}