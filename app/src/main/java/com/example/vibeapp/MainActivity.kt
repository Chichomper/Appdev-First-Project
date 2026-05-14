package com.example.vibeapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentInput = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val display = findViewById<TextView>(R.id.displayEmojis)
        val resultText = findViewById<TextView>(R.id.displayResult)
        val infoBtn = findViewById<Button>(R.id.btnInfo)
        val clearBtn = findViewById<Button>(R.id.btnClear)

        val vibeButtons = mapOf(
            R.id.btnCoffee to "☕",
            R.id.btnRain to "🌧️",
            R.id.btnSecret to "🤐",
            R.id.btnDance to "💃",
            R.id.btnSparkle to "✨",
            R.id.btnFire to "🔥",
            R.id.btnMoon to "🌙",
            R.id.btnDice to "🎲"
        )

        vibeButtons.forEach { (id, emoji) ->
            findViewById<Button>(id).setOnClickListener {
                if (currentInput.size >= 6) return@setOnClickListener
                currentInput.add(emoji)
                display.text = currentInput.joinToString(" ")
                val bounce = AnimationUtils.loadAnimation(this, R.anim.bounce)
                display.startAnimation(bounce)
                resultText.text = calculateVibe(currentInput)
            }
        }

        clearBtn.setOnClickListener {
            currentInput.clear()
            display.text = ""
            resultText.text = getString(R.string.hint_select)
        }

        infoBtn.setOnClickListener {
            val modal = InfoModal()
            modal.show(supportFragmentManager, "InfoModal")
        }
    }

    private fun calculateVibe(input: List<String>): String {
        return when {
            input.contains("☕") && input.contains("🌧️") -> "☁️  A cozy deadline."
            input.contains("💃") && input.contains("🤐") -> "🎭  A masked ball."
            input.contains("🔥") && input.contains("✨") -> "⚡  Main character energy."
            input.contains("🌙") && input.contains("☕") -> "😵  Late-night spiral."
            input.contains("🎲") && input.contains("🌧️") -> "🌀  Chaotic weather forecast."
            input.contains("💃") && input.contains("✨") && input.contains("🔥") -> "🏆  Unstoppable."
            input.size >= 5 -> "😵‍💫  Social overload!"
            else -> "🔮  Vibe TBD…"
        }
    }
}
