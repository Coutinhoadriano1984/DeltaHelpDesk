package br.unip.hermesbot.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unip.hermesbot.R
import br.unip.hermesbot.controller.ApiClient
import br.unip.hermesbot.model.LoginRequest
import br.unip.hermesbot.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : BaseActivity() {

    private val scope = MainScope()

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button

    companion object {
        const val PREFS_NAME = "delta_prefs"
        const val KEY_USER_ID = "user_id"
        const val KEY_USER_NAME = "user_name"
        const val KEY_USER_ROLE = "user_role"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etSenha = findViewById(R.id.etSenha)
        btnLogin = findViewById(R.id.btnLogin)

        // Se já está logado → Ir direto para o Dashboard
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedId = prefs.getInt(KEY_USER_ID, -1)
        val savedName = prefs.getString(KEY_USER_NAME, null)
        val savedRole = prefs.getString(KEY_USER_ROLE, "user")

        if (savedId != -1 && !savedName.isNullOrEmpty()) {
            goToDashboard(savedId, savedName!!, savedRole!!)
            return
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha email e senha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnLogin.isEnabled = false

            scope.launch {
                try {
                    val resp = withContext(Dispatchers.IO) {
                        ApiClient.service.login(LoginRequest(email, senha))
                    }

                    if (resp.isSuccessful && resp.body() != null) {
                        val body = resp.body()!!

                        saveUserToPrefs(body)
                        goToDashboard(body.usuarioId, body.nome, body.perfil)

                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Usuário ou senha incorretos",
                            Toast.LENGTH_LONG
                        ).show()
                        btnLogin.isEnabled = true
                    }

                } catch (e: Exception) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Falha de conexão: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    btnLogin.isEnabled = true
                }
            }
        }
    }

    private fun saveUserToPrefs(loginResponse: LoginResponse) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putInt(KEY_USER_ID, loginResponse.usuarioId)
            putString(KEY_USER_NAME, loginResponse.nome)
            putString(KEY_USER_ROLE, loginResponse.perfil)
            apply()
        }
    }

    private fun goToDashboard(userId: Int, userName: String, role: String) {
        val i = Intent(this, DashboardActivity::class.java)
        i.putExtra("user_id", userId)
        i.putExtra("user_name", userName)
        i.putExtra("user_role", role)
        startActivity(i)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }
}
