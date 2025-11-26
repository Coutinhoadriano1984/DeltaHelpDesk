package br.unip.hermesbot.controller

import br.unip.hermesbot.model.Chamado

class ChamadoController {

    suspend fun criarChamado(chamado: Chamado): Result<Chamado> {
        return try {
            val r = ApiClient.service.abrirChamado(chamado)
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("Erro ao criar chamado: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getChamados(): Result<List<Chamado>> {
        return try {
            val r = ApiClient.service.listarChamados()
            if (r.isSuccessful) Result.success(r.body() ?: emptyList())
            else Result.failure(Exception("Erro ao buscar chamados: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getChamado(id: Int): Result<Chamado> {
        return try {
            val r = ApiClient.service.getChamado(id)
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("Chamado não encontrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun atualizarChamado(id: Int, chamado: Chamado): Result<Unit> {
        return try {
            val r = ApiClient.service.atualizarChamado(id, chamado)
            if (r.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Erro ao atualizar chamado: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deletarChamado(id: Int): Result<Unit> {
        return try {
            val r = ApiClient.service.deletarChamado(id)
            if (r.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Erro ao deletar chamado: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
