
package dev.jefersonguerrajr.contatos_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.jefersonguerrajr.contatos_app.model.Contact

@Composable
fun ContactDetailsDialog(
    contact: Contact,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = contact.name) },
        text = {
            Column {
                Text(text = "Email: ${contact.email}")
                Text(text = "Telefone: ${contact.phone}")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Fechar")
            }
        }
    )
}
