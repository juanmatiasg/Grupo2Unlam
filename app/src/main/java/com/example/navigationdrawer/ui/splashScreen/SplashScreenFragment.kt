package com.example.navigationdrawer.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentSplashScreenBinding
import kotlinx.android.synthetic.main.app_bar_main.*
import org.koin.android.ext.android.bind


class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.motionLayout.addTransitionListener(object: MotionLayout.TransitionListener{
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

        })


        /**Handler().postDelayed({
        // Este método se ejecutará una vez que termine el temporizador
        // Inicia la actividad principal de tu aplicación

        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment) /*Cierra la actividad*/
        },3000)*/

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}