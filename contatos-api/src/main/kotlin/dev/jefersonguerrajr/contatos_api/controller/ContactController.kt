package dev.jefersonguerrajr.contatos_api.controller

import dev.jefersonguerrajr.contatos_api.model.Contact
import dev.jefersonguerrajr.contatos_api.service.ContactService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contatos", description = "API para gerenciamento de contatos")
class ContactController(private val contactService: ContactService) {

    @GetMapping
    @Operation(summary = "Listar todos os contatos", description = "Retorna a lista de todos os contatos")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved contacts")
    fun getAllContacts() = contactService.getAllcontacts()

    @GetMapping("/{id}")
    @Operation(summary = "Get contact by ID", description = "Returns a contact by its ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved contact"),
            ApiResponse(responseCode = "404", description = "Contact not found")
        ]
    )
    fun getContactById(
        @Parameter(description = "ID of the contact to retrieve", required = true)
        @PathVariable id: Long
    ) = contactService.getContactById(id)

    @PostMapping
    @Operation(summary = "Novo contato", description = "Cria um novo contato, retorna o contato criado com id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Contato criado comm sucesso"),
            ApiResponse(responseCode = "400", description = "Invalid input")
        ]
    )
    fun createContact(
        @Parameter(description = "Objecto contendo os dados do contato", required = true)
        @RequestBody contact: Contact
    ) = contactService.createNewContact(contact)

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um contato existente", description = "Atualiza um contato através do ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
            ApiResponse(responseCode = "404", description = "Contato not found")
        ]
    )
    fun updateContact(
        @Parameter(description = "ID of the contact to update", required = true)
        @PathVariable id: Long,
        @Parameter(description = "Updated contact object", required = true)
        @RequestBody contact: Contact
    ) = contactService.updateContact(contact, id)

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact", description = "Deletes a contact by its ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Contact successfully deleted"),
            ApiResponse(responseCode = "404", description = "Contact not found")
        ]
    )
    fun deleteContact(
        @Parameter(description = "ID of the contact to delete", required = true)
        @PathVariable id: Long
    ) = contactService.deleteContact(id)
}
