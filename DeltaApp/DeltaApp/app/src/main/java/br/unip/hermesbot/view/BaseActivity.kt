package br.unip.hermesbot.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.unip.hermesbot.R

open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {

        // Não aplica header na tela de login ou splash
        if (this is LoginActivity || this is SplashActivity) {
            super.setContentView(layoutResID)
            return
        }

        val inflater = LayoutInflater.from(this)
        val root = inflater.inflate(R.layout.base_with_header, null)
        val contentContainer = root.findViewById<android.widget.FrameLayout>(R.id.baseContentContainer)

        inflater.inflate(layoutResID, contentContainer, true)
        super.setContentView(root)

        setupHeader()
    }

    private fun setupHeader() {
        val btnLeft = findViewById<ImageView>(R.id.btnHeaderLeft)

        if (this is DashboardActivity) {
            btnLeft.setImageResource(R.drawable.ic_logout)
            btnLeft.setOnClickListener {
                AlertDialog.Builder(this)
                    .setTitle("Sair da Conta")
                    .setMessage("Deseja realmente deslogar?")
                    .setPositiveButton("Sim") { _, _ ->
                        val prefs = getSharedPreferences(LoginActivity.PREFS_NAME, Context.MODE_PRIVATE)
                        prefs.edit().clear().apply()

                        val i = Intent(this, LoginActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(i)
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        } else {
            btnLeft.setImageResource(R.drawable.ic_arrow_back)
            btnLeft.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }
}
