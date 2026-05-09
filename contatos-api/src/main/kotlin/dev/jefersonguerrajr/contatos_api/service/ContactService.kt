package dev.jefersonguerrajr.contatos_api.service

import dev.jefersonguerrajr.contatos_api.model.Contact
import dev.jefersonguerrajr.contatos_api.repository.ContactRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ContactService(private val contactRepository: ContactRepository) {

    fun getAllcontacts() = ResponseEntity.ok(contactRepository.findAll())

    fun getContactById(id: Long): ResponseEntity<Contact> {
        val contact = contactRepository.findById(id)
        return if (contact.isPresent) {
            ResponseEntity.ok(contact.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    fun createNewContact(contact: Contact) = ResponseEntity<Contact>.ok(contactRepository.save(contact))

    fun updateContact(contact:Contact, id:Long): ResponseEntity<Contact> {
        return if (contactRepository.existsById(id)) {
            val updatedContact = contact.copy(id = id)
            contactRepository.save(updatedContact)
            ResponseEntity.ok(updatedContact)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    fun deleteContact(id:Long): ResponseEntity<Void> {
        return if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}
