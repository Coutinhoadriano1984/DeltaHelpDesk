package br.unip.hermesbot.view

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.unip.hermesbot.R
import android.view.View

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.splashLogo)
        val title = findViewById<TextView>(R.id.splashTitle)

        // 1 — Fade in da logo
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))

        // 2 — Iniciar glitch depois de 600ms
        Handler(mainLooper).postDelayed({

            title.visibility = View.VISIBLE

            val finalText = "DELTA"
            val glitchChars = listOf('@', '#', '$', '%', '&', '!', '?', '+', '*')
            val totalDuration = 700L
            val perLetter = totalDuration / finalText.length

            var index = 0

            fun animateLetter() {
                if (index >= finalText.length) return

                val correctChar = finalText[index]
                var step = 0

                val glitchTimer = object : CountDownTimer(perLetter, 40) {
                    override fun onTick(ms: Long) {
                        step++
                        val glitchChar = glitchChars.random()
                        title.text = finalText.substring(0, index) + glitchChar
                    }

                    override fun onFinish() {
                        // coloca a letra correta
                        title.text = finalText.substring(0, index + 1)
                        index++
                        animateLetter()
                    }
                }
                glitchTimer.start()
            }

            animateLetter()

        }, 600)

        // 3 — Vai para Login depois de tudo
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1600)
    }

}
