package br.unip.hermesbot.model

data class HistoricoChamado(
    val historicoId: Int? = null,
    val chamadoId: Int? = null,
    val dataEvento: String? = null,
    val descricaoEvento: String? = null,
    val usuarioId: Int? = null
)
