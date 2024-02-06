package com.example.testchamp.session2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testchamp.R
import com.example.testchamp.databinding.SignUpBinding
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.launch


class SignUp : Fragment() {

    private lateinit var binding: SignUpBinding
    private lateinit var supabase: SupabaseClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.sign_up, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Email_get = view.findViewById<EditText>(R.id.emailET)
        val bF1 = view.findViewById<Button>(R.id.signUpBT)
        val bF2 = view.findViewById<TextView>(R.id.signIm)
        val controller = findNavController()
        bF1.setOnClickListener { controller.navigate(R.id.onBoarding2) }
        bF2.setOnClickListener { controller.navigate(R.id.logIn) }
        val supabaseUrl = "https://tnmahbgcomhugvcalfgk.supabase.co"
        val supabaseKey =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRubWFoYmdjb21odWd2Y2FsZmdrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDY3NzgwNjAsImV4cCI6MjAyMjM1NDA2MH0.6s6CPbw4ibYEtBP2FBOlf8JFA7R5dpqzJ6284ULBF0I"

        supabase = createSupabaseClient(supabaseUrl = supabaseUrl, supabaseKey = supabaseKey) {
            install(Auth)
            install(Realtime)
            install(Storage)
            install(Postgrest)
        }
        bF1.setOnClickListener {
            lifecycleScope.launch {
                val enteredEmail = Email_get.text.toString()

                val user = supabase.auth.signUpWith(Email) {
                    email = enteredEmail
                    password = "example-password"
                }
        }




       }

    }
}