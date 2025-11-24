package br.unip.hermesbot.model

data class Usuario(
    val usuarioId: Int,
    val nome: String,
    val email: String,
    val senha: String?,
    val cpf: String?,
    val telefone: String?,
    val perfil: String?,
    val dataCadastro: String?
)
