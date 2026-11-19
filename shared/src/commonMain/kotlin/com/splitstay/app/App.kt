
package com.splitstay.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Purple = Color(0xFF7653D6)
private val DarkPurple = Color(0xFF281B49)
private val LightPurple = Color(0xFFF1ECFF)
private val Background = Color(0xFFFAF9FF)

private enum class AppScreen {
    Welcome,
    Login,
    SignUp
}

@Composable
fun App() {

    var currentScreen by remember {
        mutableStateOf(AppScreen.Welcome)
    }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Purple,
            background = Background,
            surface = Color.White
        )
    ) {

        when (currentScreen) {

            AppScreen.Welcome -> {
                WelcomeScreen(
                    onGetStarted = {
                        currentScreen = AppScreen.SignUp
                    },
                    onLogin = {
                        currentScreen = AppScreen.Login
                    }
                )
            }

            AppScreen.Login -> {
                AuthScreen(
                    isLogin = true,
                    onBack = {
                        currentScreen = AppScreen.Welcome
                    },
                    onSwitchMode = {
                        currentScreen = AppScreen.SignUp
                    }
                )
            }

            AppScreen.SignUp -> {
                AuthScreen(
                    isLogin = false,
                    onBack = {
                        currentScreen = AppScreen.Welcome
                    },
                    onSwitchMode = {
                        currentScreen = AppScreen.Login
                    }
                )
            }
        }
    }
}

// WELCOME SCREEN

@Composable
private fun WelcomeScreen(
    onGetStarted: () -> Unit,
    onLogin: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(12.dp))

        Text(
            text = "💜 SplitsYay",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurple
        )

        Spacer(Modifier.height(55.dp))

        Text(
            text = "Sharing expenses,\nmade easy.",
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkPurple,
            textAlign = TextAlign.Center,
            lineHeight = 42.sp
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Split bills, track balances and settle up with friends.",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(35.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightPurple
            )
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Friends who split together",
                    color = DarkPurple,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FriendAvatar("K", Purple)
                    FriendAvatar("J", Color(0xFF269D85))
                    FriendAvatar("R", Color(0xFFEA9B4A))
                }
            }
        }

        Spacer(Modifier.height(35.dp))

        Button(
            onClick = onGetStarted,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                "Get Started",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                "I already have an account",
                color = Purple
            )
        }

        Spacer(Modifier.height(30.dp))

        Text(
            "Made for friends. Built for fair sharing. 💜",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

// LOGIN AND SIGN-UP SCREENS

@Composable
private fun AuthScreen(
    isLogin: Boolean,
    onBack: () -> Unit,
    onSwitchMode: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {

        TextButton(onClick = onBack) {
            Text("← Back", color = Purple)
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = if (isLogin) "Welcome Back! 👋"
            else "Create Account 💜",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurple
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = if (isLogin)
                "Login to continue sharing expenses with friends."
            else
                "Join SplitsYay and make expense sharing easier.",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(30.dp))

        if (!isLogin) {

            Text(
                "Full Name",
                fontWeight = FontWeight.SemiBold,
                color = DarkPurple
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    message = ""
                },
                placeholder = { Text("Enter your full name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(Modifier.height(18.dp))
        }

        Text(
            "Email Address",
            fontWeight = FontWeight.SemiBold,
            color = DarkPurple
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                message = ""
            },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(Modifier.height(18.dp))

        Text(
            "Password",
            fontWeight = FontWeight.SemiBold,
            color = DarkPurple
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                message = ""
            },
            placeholder = { Text("Enter your password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(14.dp)
        )

        if (!isLogin) {

            Spacer(Modifier.height(18.dp))

            Text(
                "Confirm Password",
                fontWeight = FontWeight.SemiBold,
                color = DarkPurple
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    message = ""
                },
                placeholder = { Text("Re-enter your password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(14.dp)
            )
        }

        Spacer(Modifier.height(26.dp))

        Button(
            onClick = {

                message = when {

                    !isLogin && name.isBlank() ->
                        "Please enter your name."

                    email.isBlank() || !email.contains("@") ->
                        "Please enter a valid email address."

                    password.length < 6 ->
                        "Password must contain at least 6 characters."

                    !isLogin && password != confirmPassword ->
                        "Passwords do not match."

                    else ->
                        "Form validated! Account services will be connected next."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = if (isLogin) "Login" else "Create Account",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (message.isNotEmpty()) {

            Spacer(Modifier.height(16.dp))

            Text(
                text = message,
                color = Purple,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = if (isLogin)
                    "New to SplitsYay? "
                else
                    "Already have an account? ",
                color = Color.Gray,
                fontSize = 13.sp
            )

            TextButton(onClick = onSwitchMode) {
                Text(
                    text = if (isLogin) "Sign Up" else "Login",
                    color = Purple,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// FRIEND AVATAR

@Composable
private fun FriendAvatar(
    initial: String,
    color: Color
) {

    Surface(
        modifier = Modifier.size(55.dp),
        shape = CircleShape,
        color = color
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = initial,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
