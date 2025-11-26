package br.unip.hermesbot.controller

import android.content.Context
import br.unip.hermesbot.model.LoginRequest
import br.unip.hermesbot.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginController(private val ctx: Context) {

    suspend fun attemptLogin(email: String, senha: String): Result<LoginResponse> =
        withContext(Dispatchers.IO) {
            try {
                val r = ApiClient.service.login(LoginRequest(email, senha))

                if (r.isSuccessful) {
                    val body = r.body()

                    if (body != null) {
                        // LOGIN OK
                        Result.success(body)
                    } else {
                        Result.failure(Exception("Usuário ou senha incorretos"))
                    }
                } else {
                    Result.failure(Exception("Erro ${r.code()}: Usuário ou senha incorretos"))
                }

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
