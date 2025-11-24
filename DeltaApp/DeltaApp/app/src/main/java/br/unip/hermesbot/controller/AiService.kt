package br.unip.hermesbot.network

import br.unip.hermesbot.model.AiRequest
import br.unip.hermesbot.model.AiResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// -------- CONFIGURAÇÃO DA API DE IA (AZURE, OPENAI, ETC) --------

private const val AI_BASE_URL = "https://SUA-AZURE.openai.azure.com/"
private const val AI_API_KEY = "SUA_CHAVE_AQUI"
private const val DEPLOYMENT = "SEU_NOME_DO_MODELO"

// CLIENT COM AUTORIZAÇÃO
private val aiClient = OkHttpClient.Builder()
    .addInterceptor(Interceptor { chain ->
        val req = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("api-key", AI_API_KEY)
            .build()
        chain.proceed(req)
    })
    .build()

private val retrofitAI = Retrofit.Builder()
    .baseUrl(AI_BASE_URL)
    .client(aiClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// ------------- ENDPOINT DA IA --------------------

interface AiService {

    @POST("openai/deployments/$DEPLOYMENT/chat/completions?api-version=2023-05-15")
    @Headers("Content-Type: application/json")
    suspend fun askFaq(@Body req: AiRequest): AiResponse

    companion object {
        val instance: AiService by lazy {
            retrofitAI.create(AiService::class.java)
        }
    }
}
