package com.purity.eatwise.navigation

import NutritionTrackerScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.purity.eatwise.data.UserDatabase
import com.purity.eatwise.repository.UserRepository
import com.purity.eatwise.ui.theme.screens.splash.SplashScreen
import com.purity.eatwise.viewmodel.AuthViewModel
import com.purity.eatwise.ui.screens.auth.LoginScreen
import com.purity.eatwise.ui.screens.auth.RegisterScreen
import com.purity.eatwise.ui.theme.screens.home.HomeScreen
import com.purity.eatwise.ui.theme.screens.mealplanner.MealPlannerScreen
import com.purity.eatwise.ui.theme.screens.products.AddProductScreen
import com.purity.eatwise.ui.theme.screens.products.EditProductScreen
import com.purity.eatwise.ui.theme.screens.products.ProductListScreen
import com.purity.eatwise.ui.theme.screens.quickrecipes.QuickRecipesScreen
import com.purity.eatwise.ui.theme.screens.tips.TipsScreen
import com.purity.eatwise.viewmodel.ProductViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(

    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    productViewModel: ProductViewModel = viewModel(),

    ) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {





        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_MEAL) {
            MealPlannerScreen(navController)
        }
        composable(ROUT_NUTRITION) {
            NutritionTrackerScreen(navController)
        }
        composable(ROUT_RECIPES) {
            QuickRecipesScreen(navController)
        }
        composable(ROUT_TIPS) {
            TipsScreen(navController)
        }
        composable(ROUT_MEAL) {
            MealPlannerScreen(navController)
        }
        composable(ROUT_HOME) {
            HomeScreen(
                navController
            )
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
        // PRODUCTS
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }
    }
}


