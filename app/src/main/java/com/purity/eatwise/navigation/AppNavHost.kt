package com.purity.eatwise.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.data.UserDatabase
import com.purity.eatwise.repository.UserRepository
import com.purity.eatwise.ui.theme.screens.home.HomeScreen
import com.purity.eatwise.ui.theme.screens.splash.SplashScreen
import com.purity.eatwise.viewmodel.AuthViewModel
import com.purity.sokomart.ui.screens.auth.LoginScreen
import com.purity.sokomart.ui.screens.auth.RegisterScreen


@Composable
fun AppNavHost(

    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen()
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }



        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }


        }
    }
}

