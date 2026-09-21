package com.example.mygirlfriend

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class VoiceManager(context: Context, onReady: () -> Unit) {
    private var tts: TextToSpeech? = null
    var isReady = false

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val locale = Locale("bn", "IN")
                tts?.language = locale

                tts?.voices?.forEach { voice ->
                    if (voice.locale.language == "bn" && voice.name.contains("female", ignoreCase = true)) {
                        tts?.voice = voice
                    }
                }

                tts?.setPitch(1.15f)
                tts?.setSpeechRate(0.95f)
                isReady = true
                onReady()
            }
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "GF_SPEECH")
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
