package br.unip.hermesbot.model

data class Chamado(
    val chamadoId: Int = 0,
    val titulo: String,
    val descricao: String,
    val status: String,
    val prioridade: String,
    val dataAbertura: String,
    val dataFechamento: String? = null,
    val usuarioSolicitanteId: Int?,
    val tecnicoResponsavelId: Int?,
    val categoriaId: Int?
)

