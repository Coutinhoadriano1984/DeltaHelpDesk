package br.unip.hermesbot.model

data class AiRequest(
    val messages: List<Message>,
    val temperature: Double = 0.1,
    val max_tokens: Int = 300
)

data class Message(
    val role: String,
    val content: String
)
