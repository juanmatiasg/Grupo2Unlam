package com.example.navigationdrawer.ui.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.room.Update
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.fragment_login.*
import android.content.Intent as intent

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var email: String= ""
    private var password: String= ""
    private lateinit var auth: FirebaseAuth
    private val GOOGLE_SING_IN = 1234


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrarAquiButton.setOnClickListener { navigateToRegister() }
        binding.buttonIniciarSesion.setOnClickListener { login() }
        binding.loginGoogle.setOnClickListener{ loginWithGoogle()}
    }

    private fun login() {
        email = binding.editTextEmailLogin.text.toString()
        password = binding.editTextPasswordLogin.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                        navigateToHome()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Authentication failed",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (task.exception is FirebaseAuthInvalidUserException) {
                            Toast.makeText(
                                requireContext(),
                                "El usuario no existe",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(
                                requireContext(),
                                "Credenciales invalidas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
        }else{
            Toast.makeText(requireContext(),"Ingrese su email y contraseña",Toast.LENGTH_SHORT).show()
        }

    }

    private fun loginWithGoogle() {
        val googleConf =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn.getClient(this.activity, googleConf)
        startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SING_IN)
    }

    private fun navigateToRegister() {
        //findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
    private fun navigateToHome(){
        //findNavController().navigate(R.id.action_loginFragment_to_nav_home)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: android.content.Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SING_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(
                    requireContext(),
                    "Authentication failed2",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {

                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                    navigateToHome()
                } else {

                    Toast.makeText(
                        requireContext(),
                        "Authentication failed",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {

    }
}

