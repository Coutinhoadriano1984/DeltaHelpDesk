package br.unip.hermesbot.controller

import br.unip.hermesbot.model.Faq

class FaqController {

    suspend fun getFaqs(): Result<List<Faq>> {
        return try {
            val r = ApiClient.service.listarFaqs()
            if (r.isSuccessful) Result.success(r.body() ?: emptyList())
            else Result.failure(Exception("Erro: ${r.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFaq(id: Int): Result<Faq> {
        return try {
            val r = ApiClient.service.getFaq(id)
            if (r.isSuccessful && r.body() != null) Result.success(r.body()!!)
            else Result.failure(Exception("FAQ não encontrada"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
