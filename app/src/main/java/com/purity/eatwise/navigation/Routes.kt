package com.purity.eatwise.navigation

const val ROUT_HOME = "home"
const val ROUT_SPLASH= "splash"
const val ROUT_MEAL= "meal"
const val ROUT_NUTRITION= "nutrition"
const val ROUT_RECIPES= "recipes"
const val ROUT_TIPS= "tips"

//auth
const val ROUT_REGISTER = "register"
const val ROUT_LOGIN = "login"


//Products

const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"
// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"
