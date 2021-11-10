package com.example.navigationdrawer.ui.camera

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentCameraBinding
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.text.SimpleDateFormat
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.ui.camera.usecases.BarCode
import java.util.*


class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private lateinit var executorCamera: ExecutorService
    private lateinit var outputDirectory: File

    private var imageCapture: ImageCapture? = null
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        executorCamera =
            Executors.newSingleThreadExecutor() /*Es un hilo(thread) especifico para que corra la camara*/ /*No puede corren en el Main Thread UI*/ /*Esta ejecutandose en otro hilo en background */
        outputDirectory = getOutPutDirectory()

        startCamera()

        binding.cameraCaptureButton.setOnClickListener { takePhoto() }
        binding.flipCamera.setOnClickListener { voltearLaCamara() }


    }

    @SuppressLint("NewApi")
    private fun getOutPutDirectory(): File {
        val mediaDir = activity?.externalMediaDirs!!.firstOrNull()?.let {
            File(it, "Camerax").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else activity!!.filesDir
    }

    private fun startCamera() {
        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(requireContext()) /*->Obtengo la instancia del Procesamiento */ /*El providerProviderFuture viene a remplazar los Asyntask , en algun momento va estar listo*/

        cameraProviderFuture.addListener({
            /*Enlanzamos el ciclo de vide del Activity */
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            /*Generamos el Preview*/ /*Lo primero que necesito es que se vea la camara*/
            val preview = Preview.Builder().build()
                .also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }
            /*Generamos el Image Capture*/
            imageCapture = ImageCapture.Builder()
                .build()

            /*Generamos un  ImageAnalyzer de BarCode*/
            val barCodeAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(executorCamera, BarCode())
                }

            try {
                /*Si existe algun Provider Enlanzado , lo desenlazamos*/
                cameraProvider.unbindAll()

                /*Enlanzamos los casos de usos*/
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    barCodeAnalyzer,
                    preview,
                    imageCapture,

                )

            } catch (e: Exception) {
                Log.e("ErrorDeCamara", "Falló al enlanzar la cámara: $e")
            }

        }, ContextCompat.getMainExecutor(requireContext())) /*Cuando termina e listener*/

    }

    private fun takePhoto() {
        // Obtenemos la referencia a la captura de la imagen
        val imageCapture = imageCapture ?: return

        // Creamos un archivo para almacenar la imagen utilizando un timestamp
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        // Creamos un OutPutFileOptions con la metadata para almacenar la imagen
        // utilizando el metodo .setMetadata() del builder podemos agregar mas metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
            .build()

        // Creamos un listener asociado a imageCapture que escuche cuando se tomo una fotografia
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Error al capturar imagen: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Foto capturado con exito: $savedUri"
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                }
            }
        )
    }


    private fun voltearLaCamara() {
        cameraSelector =
            if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                CameraSelector.DEFAULT_FRONT_CAMERA
            else
                CameraSelector.DEFAULT_BACK_CAMERA

        startCamera() /*Aca no olvidemos porque tenemos que desbindear*/

    }

    override fun onDestroy() {
        super.onDestroy()
        executorCamera.shutdown()
        _binding = null
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val TAG = "CameraActivity"
    }

}