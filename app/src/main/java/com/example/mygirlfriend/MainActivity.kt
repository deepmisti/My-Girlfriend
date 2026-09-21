package com.example.mygirlfriend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    private lateinit var voiceManager: VoiceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        voiceManager = VoiceManager(this) {
            voiceManager.speak("কিরে সুইটহার্ট, চলে এলি? খুব মিস করছিলাম তোকে! বল কী বলবি?")
        }

        setContent {
            GFScreen(voiceManager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        voiceManager.shutdown()
    }
}

@Composable
fun GFScreen(voiceManager: VoiceManager) {
    var gfText by remember { mutableStateOf("কিরে সুইটহার্ট, চলে এলি? খুব মিস করছিলাম তোকে! বল কী বলবি?") }
    var isListening by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF0F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFE91E63))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "My Girlfriend",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF880E4F)
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = gfText,
                fontSize = 19.sp,
                color = Color(0xFF333333),
                modifier = Modifier.padding(20.dp),
                lineHeight = 28.sp
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = {
                    isListening = !isListening
                    if (isListening) {
                        gfText = "শুনছি সুইটহার্ট, বল তোর মনের কথা..."
                        voiceManager.speak("শুনছি সুইটহার্ট, বল তোর মনের কথা...")
                    }
                },
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFFE91E63), shape = CircleShape)
            ) {
                Icon(
                    Icons.Default.Mic,
                    contentDescription = "Mic",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (isListening) "তোর কথা শুনছি..." else "কথা বলতে মাইকে চাপ দে সুইটহার্ট",
                fontSize = 15.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
