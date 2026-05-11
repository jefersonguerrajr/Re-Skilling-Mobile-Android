package dev.jefersonguerrajr.contatos_api.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val birthDate: LocalDate = LocalDate.now(),
    val cep: String = "",
    val neighborhood: String = "",
    val street: String = "",
    val number: String = "",
    val state: String = "",
    val city: String = ""
)
