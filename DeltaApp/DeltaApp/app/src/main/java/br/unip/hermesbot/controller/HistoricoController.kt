package br.unip.hermesbot.controller

import br.unip.hermesbot.model.HistoricoChamado

class HistoricoController {

    suspend fun getHistoricos(): Result<List<HistoricoChamado>> {
        return try {
            val r = ApiClient.service.listarHistoricos()
            if (r.isSuccessful) Result.success(r.body() ?: emptyList())
            else Result.failure(Exception("Erro: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getHistorico(id: Int): Result<HistoricoChamado> {
        return try {
            val r = ApiClient.service.getHistorico(id)
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("Histórico não encontrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
