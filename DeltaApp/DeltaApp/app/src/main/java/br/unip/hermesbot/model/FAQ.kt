package br.unip.hermesbot.model

data class Faq(
    val faqId: Int,
    val pergunta: String,
    val resposta: String,
    val categoriaId: Int?
)

