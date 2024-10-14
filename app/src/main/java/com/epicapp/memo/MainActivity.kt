package com.epicapp.memo

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.memoryView.view.MemoryViewScreen
import com.epicapp.memo.ui.menu.view.MenuScreen
import com.epicapp.memo.ui.profile.view.ProfileScreen
import com.epicapp.memo.ui.singin.view.SingIn

import com.epicapp.memo.ui.theme.MeMoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeMoTheme {
                SingIn(onLoginClick = { /*TODO*/ }) {
                }
                }
            }
        }
    }

