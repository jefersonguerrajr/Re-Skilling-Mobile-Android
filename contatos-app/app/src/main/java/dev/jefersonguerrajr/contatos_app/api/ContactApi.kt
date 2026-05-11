
package dev.jefersonguerrajr.contatos_app.api

import dev.jefersonguerrajr.contatos_app.model.Contact
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ContactApi {
    @GET("api/contacts")
    suspend fun getAll(): List<Contact>

    @GET("api/contacts/{id}")
    suspend fun getById(@Path("id") id: Long): Contact

    @POST("api/contacts")
    suspend fun create(@Body contact: Contact): Contact

    @PUT("api/contacts/{id}")
    suspend fun update(@Path("id") id: Long, @Body contact: Contact): Contact

    @DELETE("api/contacts/{id}")
    suspend fun delete(@Path("id") id: Long)
}
