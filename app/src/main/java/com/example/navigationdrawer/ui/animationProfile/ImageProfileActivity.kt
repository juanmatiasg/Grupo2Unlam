package com.example.navigationdrawer.ui.animationProfile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.navigationdrawer.data.model.User

class ImageProfileActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getSerializableExtra(EXTRA_PARAMS) as User

        setContent {
            ImageUserDetailScreen(user)
        }

    }

    @ExperimentalAnimationApi
    @Composable
    private fun ImageUserDetailScreen(user: User) {
        Scaffold(
            content = {
                ImageUser(user)
            }
        )
    }

    @ExperimentalAnimationApi
    @Composable
    private fun ImageUser(user: User) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserAvatar(user.image)
        }
    }

    companion object {
        const val EXTRA_PARAMS = "EXTRA_IMAGE_PROFILE"
    }


    @ExperimentalAnimationApi
    @Composable
    fun UserAvatar(@DrawableRes image: Int) {
        var editable by remember { mutableStateOf(true) }
        AnimatedVisibility(
            visible = editable,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Image(
                painter = painterResource(id = image),
                modifier = Modifier
                    .size(240.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .shadow(8.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }

    }
}

