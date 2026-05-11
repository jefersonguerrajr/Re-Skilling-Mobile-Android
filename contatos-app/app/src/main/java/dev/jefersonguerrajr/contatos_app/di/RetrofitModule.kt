
package dev.jefersonguerrajr.contatos_app.di

import dev.jefersonguerrajr.contatos_app.api.ContactApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val contactApi: ContactApi = retrofit.create(ContactApi::class.java)
}
