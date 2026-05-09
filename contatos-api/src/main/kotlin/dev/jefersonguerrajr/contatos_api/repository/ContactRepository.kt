package dev.jefersonguerrajr.contatos_api.repository

import dev.jefersonguerrajr.contatos_api.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long>
