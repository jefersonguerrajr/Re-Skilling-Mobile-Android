package dev.jefersonguerrajr.contatos_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jefersonguerrajr.contatos_app.di.RetrofitModule
import dev.jefersonguerrajr.contatos_app.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            try {
                _contacts.value = RetrofitModule.contactApi.getAll()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            try {
                val newContact = RetrofitModule.contactApi.create(contact)
                _contacts.value = _contacts.value + newContact
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            try {
                contact.id?.let {
                    id ->
                    val updatedContact = RetrofitModule.contactApi.update(id, contact)
                    _contacts.value = _contacts.value.map {
                        if (it.id == updatedContact.id) updatedContact else it
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            try {
                contact.id?.let {
                    id ->
                    RetrofitModule.contactApi.delete(id)
                    _contacts.value = _contacts.value.filter { it.id != id }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}