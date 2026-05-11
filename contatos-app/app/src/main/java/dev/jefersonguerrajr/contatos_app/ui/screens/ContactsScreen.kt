
package dev.jefersonguerrajr.contatos_app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.jefersonguerrajr.contatos_app.model.Contact
import dev.jefersonguerrajr.contatos_app.ui.components.ContactDetailsDialog
import dev.jefersonguerrajr.contatos_app.ui.components.ContactForm
import dev.jefersonguerrajr.contatos_app.ui.components.ContactList
import dev.jefersonguerrajr.contatos_app.viewmodel.ContactsViewModel
import java.time.LocalDate

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = viewModel()
) {
    val contacts by viewModel.contacts.collectAsState()
    var showAddContactDialog by remember { mutableStateOf(false) }
    var showEditContactDialog by remember { mutableStateOf<Contact?>(null) }
    var showDeleteContactDialog by remember { mutableStateOf<Contact?>(null) }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var tempContact by remember { mutableStateOf<Contact?>(null) }

    Scaffold(
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meus Contatos",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = { showAddContactDialog = true }, modifier = Modifier.padding(bottom = 16.dp)) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Contato")
                Text(" Adicionar Contato")
            }
            Box(modifier = Modifier.weight(1f)) {
                ContactList(
                    contacts = contacts,
                    onContactClick = { selectedContact = it },
                    onEditClick = { showEditContactDialog = it },
                    onDeleteClick = { showDeleteContactDialog = it }
                )
            }
        }
    }

    if (showAddContactDialog) {
        tempContact = remember { Contact(id = null, name = "", email = "", phone = "", birthDate = LocalDate.now(), cep = "", street = "", number = "", neighborhood = "", city = "", state = "") }
        AlertDialog(
            onDismissRequest = { showAddContactDialog = false },
            title = { Text(text = "Adicionar Contato") },
            text = {
                ContactForm(contact = tempContact!!) { updatedContact ->
                    tempContact = updatedContact
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addContact(tempContact!!)
                    showAddContactDialog = false
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                Button(onClick = { showAddContactDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    showEditContactDialog?.let {
        contact ->
        tempContact = contact
        AlertDialog(
            onDismissRequest = { showEditContactDialog = null },
            title = { Text(text = "Editar Contato") },
            text = {
                ContactForm(contact = tempContact!!) { updatedContact ->
                    tempContact = updatedContact
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateContact(tempContact!!)
                    showEditContactDialog = null
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                Button(onClick = { showEditContactDialog = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    showDeleteContactDialog?.let {
        contact ->
        AlertDialog(
            onDismissRequest = { showDeleteContactDialog = null },
            title = { Text(text = "Excluir Contato") },
            text = { Text("Tem certeza que deseja excluir este contato?") },
            confirmButton = {
                Button(onClick = {
                    viewModel.deleteContact(contact)
                    showDeleteContactDialog = null
                }) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteContactDialog = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    selectedContact?.let {
        contact ->
        ContactDetailsDialog(contact = contact) {
            selectedContact = null
        }
    }
}