package br.unip.hermesbot.model

data class LoginResponse(
    val usuarioId: Int,
    val nome: String,
    val perfil: String
)
