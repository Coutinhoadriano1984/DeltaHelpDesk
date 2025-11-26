package br.unip.hermesbot.controller

import br.unip.hermesbot.model.Categoria

class CategoriaController {

    suspend fun getCategorias(): Result<List<Categoria>> {
        return try {
            val r = ApiClient.service.listarCategorias()
            if (r.isSuccessful) Result.success(r.body() ?: emptyList())
            else Result.failure(Exception("Erro: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCategoria(id: Int): Result<Categoria> {
        return try {
            val r = ApiClient.service.getCategoria(id)
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("Categoria não encontrada"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
