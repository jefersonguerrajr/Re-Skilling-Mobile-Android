
package dev.jefersonguerrajr.contatos_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jefersonguerrajr.contatos_app.di.ViaCepRetrofitModule
import dev.jefersonguerrajr.contatos_app.model.Contact
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactForm(
    contact: Contact,
    onContactChange: (Contact) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = contact.birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )

    LaunchedEffect(contact.cep) {
        if (contact.cep.length == 8) {
            try {
                val address = ViaCepRetrofitModule.viaCepApi.getAddress(contact.cep)
                onContactChange(
                    contact.copy(
                        street = address.logradouro,
                        neighborhood = address.bairro,
                        city = address.localidade,
                        state = address.uf
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = contact.name,
            onValueChange = { onContactChange(contact.copy(name = it)) },
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.email,
            onValueChange = { onContactChange(contact.copy(email = it)) },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.phone,
            onValueChange = { onContactChange(contact.copy(phone = it)) },
            label = { Text("Telefone") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            onValueChange = {},
            label = { Text("Data de Nascimento") },
            readOnly = true,
            modifier = Modifier.clickable { showDatePicker = true }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.cep,
            onValueChange = { onContactChange(contact.copy(cep = it)) },
            label = { Text("CEP") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.street,
            onValueChange = { onContactChange(contact.copy(street = it)) },
            label = { Text("Rua") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.number,
            onValueChange = { onContactChange(contact.copy(number = it)) },
            label = { Text("Número") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.neighborhood,
            onValueChange = { onContactChange(contact.copy(neighborhood = it)) },
            label = { Text("Bairro") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.city,
            onValueChange = { onContactChange(contact.copy(city = it)) },
            label = { Text("Cidade") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contact.state,
            onValueChange = { onContactChange(contact.copy(state = it)) },
            label = { Text("Estado") }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                            onContactChange(contact.copy(birthDate = selectedDate))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
