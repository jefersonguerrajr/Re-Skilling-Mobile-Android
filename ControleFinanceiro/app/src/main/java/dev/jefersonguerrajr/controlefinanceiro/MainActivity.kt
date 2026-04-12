package dev.jefersonguerrajr.controlefinanceiro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.jefersonguerrajr.controlefinanceiro.ui.screens.home.HomeScreen
import dev.jefersonguerrajr.controlefinanceiro.ui.screens.revenue_and_expenses.RevenueAndExpensesScreen
import dev.jefersonguerrajr.controlefinanceiro.ui.theme.ControleFinanceiroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControleFinanceiroTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            composable("home") {
                HomeScreen(onNavigateToRevenueExpenses = { type ->
                    navController.navigate("revenue_expenses/$type")
                })
            }
            composable(
                route = "revenue_expenses/{type}",
                arguments = listOf(navArgument("type") { type = NavType.StringType })
            ) { backStackEntry ->
                RevenueAndExpensesScreen(type = backStackEntry.arguments?.getString("type"))
            }
        }
    }
}
