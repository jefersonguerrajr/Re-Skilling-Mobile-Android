package dev.jefersonguerrajr.contatos_app.di

import dev.jefersonguerrajr.contatos_app.api.ViaCepApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ViaCepRetrofitModule {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val viaCepApi: ViaCepApi = retrofit.create(ViaCepApi::class.java)
}
