package com.example.navigationdrawer.ui.camera.usecases

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarCode(): ImageAnalysis.Analyzer {

    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_EAN_8
        )
        .build()


    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(image: ImageProxy) {

        image.image?.run {
            val image = InputImage.fromMediaImage(this, image.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()

            val result = scanner.process(image).addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    val bounds = barcode.boundingBox
                    val corners = barcode.cornerPoints

                    val rawValue = barcode.rawValue

                    Log.i("barCodeScanner", "El contenido es: $rawValue")

                    val valueType = barcode.valueType
                    // See API reference for complete list of supported types
                    when (valueType) {
                        Barcode.TYPE_WIFI -> {
                            val ssid = barcode.wifi!!.ssid
                            val password = barcode.wifi!!.password
                            val type = barcode.wifi!!.encryptionType

                            Log.i(
                                "barCodeScanner",
                                "type: $type ssid: $ssid password $password"
                            )
                        }
                        Barcode.TYPE_URL -> {
                            val title = barcode.url!!.title
                            val url = barcode.url!!.url

                            Log.i("barCodeScanner", "title: $title url $url")
                        }
                    }
                }
            }.addOnFailureListener {
                Log.e("barCodeScanner", it.message, it)
            }
        }
    }


}
