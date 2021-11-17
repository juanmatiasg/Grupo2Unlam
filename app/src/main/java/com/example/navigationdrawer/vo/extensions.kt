package com.example.navigationdrawer.vo

import androidx.compose.ui.platform.ComposeView
import com.google.android.gms.maps.model.LatLng

fun LatLng.toUrlParam(): String {
    return "${this.latitude},${this.longitude}"
}
