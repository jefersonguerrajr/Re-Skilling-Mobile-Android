package dev.jefersonguerrajr.contatos_app.model

import java.time.LocalDate

data class Contact(
    val id: Long? = null,
    val name: String,
    val email: String,
    val phone: String,
    val birthDate: String,
    val cep: String,
    val neighborhood: String,
    val street: String,
    val number: String,
    val state: String,
    val city: String
)
