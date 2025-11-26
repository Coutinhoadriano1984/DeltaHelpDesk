package br.unip.hermesbot.model

data class StatsResponse(
    val concluidos: Int,
    val emAndamento: Int,
    val atrasados: Int
)
