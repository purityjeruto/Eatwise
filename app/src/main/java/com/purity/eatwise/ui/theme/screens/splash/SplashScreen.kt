package com.purity.eatwise.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.purity.eatwise.R

import com.purity.eatwise.navigation.ROUT_LOGIN
import com.purity.eatwise.navigation.ROUT_REGISTER
import com.purity.eatwise.ui.theme.newNavy

import com.purity.eatwise.ui.theme.newOrange


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {

    val coroutine= rememberCoroutineScope()
    coroutine.launch{
        delay(1000)
        navController.navigate(ROUT_LOGIN)

    }
    Column (modifier = Modifier.fillMaxSize().background(newNavy), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

        Image(
            painter = painterResource(
                R.drawable.img_2
            ),
            contentDescription = "",
            modifier=Modifier.size(300.dp)

        )

        Spacer(modifier =Modifier.height(20.dp))

        Text(
            text = "let's make our body fit",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,

            )











    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(navController= rememberNavController())
}