package br.unip.hermesbot.controller

import br.unip.hermesbot.model.LoginRequest
import br.unip.hermesbot.model.LoginResponse
import br.unip.hermesbot.model.Usuario

class UsuarioController {

    suspend fun login(email: String, senha: String): Result<LoginResponse> {
        return try {
            val r = ApiClient.service.login(LoginRequest(email, senha))
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("Usuário ou senha incorretos"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUsuarios(): Result<List<Usuario>> {
        return try {
            val r = ApiClient.service.getUsuarios()
            if (r.isSuccessful) Result.success(r.body() ?: emptyList())
            else Result.failure(Exception("Erro ao buscar usuários: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUsuario(id: Int): Result<Usuario> {
        return try {
            val r = ApiClient.service.getUsuario(id)
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("Usuário não encontrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
