package br.unip.hermesbot.controller

import br.unip.hermesbot.model.Chamado

class DashboardController(
    private val chamadoController: ChamadoController = ChamadoController()
) {

    suspend fun carregarChamados(role: String, userId: Int): Result<List<Chamado>> {
        val todos = chamadoController.getChamados()

        return when {
            todos.isFailure -> Result.failure(todos.exceptionOrNull()!!)
            role == "admin" -> todos
            else -> {
                // Filtro manual porque sua API não tem filtro
                val lista = todos.getOrNull()!!.filter {
                    it.usuarioSolicitanteId == userId
                }
                Result.success(lista)
            }
        }
    }
}
