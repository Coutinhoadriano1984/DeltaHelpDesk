package br.unip.hermesbot.model

data class AiResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: AiMessage
)

data class AiMessage(
    val role: String,
    val content: String
)
