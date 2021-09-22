package com.example.navigationdrawer.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import kotlinx.android.synthetic.main.app_bar_main.*


class SplashScreenFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            // Este método se ejecutará una vez que termine el temporizador
            // Inicia la actividad principal de tu aplicación

            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment) /*Cierra la actividad*/
        },3000)

    }




}