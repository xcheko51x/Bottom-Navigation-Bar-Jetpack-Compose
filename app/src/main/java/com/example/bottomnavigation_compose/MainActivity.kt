package com.example.bottomnavigation_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation_compose.ui.theme.BottomNavigation_ComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigation_ComposeTheme {
                val navigationController = rememberNavController()

                Scaffold(
                    bottomBar = { MenuBottomNavigation(navController = navigationController) }
                ) {
                    NavigationGraph(navController = navigationController)
                }
            }
        }
    }
}

@Composable
fun MenuBottomNavigation(navController: NavController) {
    val pantallas = listOf(
        Routes.PantallaInicio,
        Routes.PantallaUsuarios,
        Routes.PantallaFavoritos
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        pantallas.forEach {pantalla ->
            BottomNavigationItem(
                selected = currentRoute == pantalla.route,
                onClick = { navController.navigate(pantalla.route)},
                icon = { Icon(painterResource(id = pantalla.icon), contentDescription = pantalla.titulo) },
                label = { Text(text = pantalla.titulo) }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.PantallaInicio.route
    ) {
        composable(Routes.PantallaInicio.route) { PantallaInicio() }
        composable(Routes.PantallaUsuarios.route) { PantallaUsuarios() }
        composable(Routes.PantallaFavoritos.route) { PantallaFavoritos() }
    }
}
