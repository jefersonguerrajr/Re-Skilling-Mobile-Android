package dev.jefersonguerrajr.contatos_app.api

import dev.jefersonguerrajr.contatos_app.model.ViaCepAddress
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {
    @GET("ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): ViaCepAddress
}
