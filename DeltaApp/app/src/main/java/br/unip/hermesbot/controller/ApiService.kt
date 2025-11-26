package br.unip.hermesbot.controller

import br.unip.hermesbot.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // USUÁRIOS
    @POST("api/Usuarios/login")
    suspend fun login(@Body login: LoginRequest): Response<LoginResponse>

    @GET("api/Usuarios")
    suspend fun getUsuarios(): Response<List<Usuario>>

    @GET("api/Usuarios/{id}")
    suspend fun getUsuario(@Path("id") id: Int): Response<Usuario>

    @POST("api/Usuarios")
    suspend fun criarUsuario(@Body usuario: Usuario): Response<Usuario>

    @PUT("api/Usuarios/{id}")
    suspend fun atualizarUsuario(@Path("id") id: Int, @Body usuario: Usuario): Response<Unit>

    @DELETE("api/Usuarios/{id}")
    suspend fun deletarUsuario(@Path("id") id: Int): Response<Unit>


    // CHAMADOS
    @GET("api/Chamados")
    suspend fun listarChamados(): Response<List<Chamado>>

    @GET("api/Chamados/{id}")
    suspend fun getChamado(@Path("id") id: Int): Response<Chamado>

    @POST("api/Chamados")
    suspend fun abrirChamado(@Body chamado: Chamado): Response<Chamado>

    @PUT("api/Chamados/{id}")
    suspend fun atualizarChamado(@Path("id") id: Int, @Body chamado: Chamado): Response<Unit>

    @DELETE("api/Chamados/{id}")
    suspend fun deletarChamado(@Path("id") id: Int): Response<Unit>

    @PUT("api/Chamados/{id}/fechar")
    suspend fun fecharChamado(
        @Header("Authorization") token: String,
        @Path("id") id: Int): Response<Unit>



    // CATEGORIAS
    @GET("api/Categorias")
    suspend fun listarCategorias(): Response<List<Categoria>>

    @GET("api/Categorias/{id}")
    suspend fun getCategoria(@Path("id") id: Int): Response<Categoria>

    @POST("api/Categorias")
    suspend fun criarCategoria(@Body categoria: Categoria): Response<Categoria>

    @PUT("api/Categorias/{id}")
    suspend fun atualizarCategoria(@Path("id") id: Int, @Body categoria: Categoria): Response<Unit>

    @DELETE("api/Categorias/{id}")
    suspend fun deletarCategoria(@Path("id") id: Int): Response<Unit>


    // FAQ
    @GET("api/FAQs")
    suspend fun listarFaqs(): Response<List<Faq>>

    @GET("api/FAQs/{id}")
    suspend fun getFaq(@Path("id") id: Int): Response<Faq>

    @POST("api/FAQs")
    suspend fun criarFaq(@Body faq: Faq): Response<Faq>

    @PUT("api/FAQs/{id}")
    suspend fun atualizarFaq(@Path("id") id: Int, @Body faq: Faq): Response<Unit>

    @DELETE("api/FAQs/{id}")
    suspend fun deletarFaq(@Path("id") id: Int): Response<Unit>


    // HISTÓRICO
    @GET("api/HistoricoChamadoes")
    suspend fun listarHistoricos(): Response<List<HistoricoChamado>>

    @GET("api/HistoricoChamadoes/{id}")
    suspend fun getHistorico(@Path("id") id: Int): Response<HistoricoChamado>

    @POST("api/HistoricoChamadoes")
    suspend fun criarHistorico(@Body historico: HistoricoChamado): Response<HistoricoChamado>

    @PUT("api/HistoricoChamadoes/{id}")
    suspend fun atualizarHistorico(@Path("id") id: Int, @Body historico: HistoricoChamado): Response<Unit>

    @DELETE("api/HistoricoChamadoes/{id}")
    suspend fun deletarHistorico(@Path("id") id: Int): Response<Unit>
}
