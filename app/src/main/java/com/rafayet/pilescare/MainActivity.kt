package com.rafayet.pilescare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF16845B)
private val LightGreen = Color(0xFFEAF6F0)
private val Dark = Color(0xFF18352B)
private val Background = Color(0xFFF7FAF8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PilesCareApp()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PilesCareApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Green,
            background = Background,
            surface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Background,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                "Piles Care",
                                fontWeight = FontWeight.Bold,
                                color = Dark
                            )
                            Text(
                                "Your daily wellness companion",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { selectedTab = 3 }) {
                            Icon(Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                tint = Green)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    val items = listOf(
                        Triple("Home", Icons.Default.Home, 0),
                        Triple("Food", Icons.Default.Restaurant, 1),
                        Triple("Tracker", Icons.Default.CalendarMonth, 2),
                        Triple("Profile", Icons.Default.Person, 3)
                    )

                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedTab == item.third,
                            onClick = { selectedTab = item.third },
                            icon = {
                                Icon(item.second, contentDescription = item.first)
                            },
                            label = { Text(item.first) }
                        )
                    }
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (selectedTab) {
                    0 -> HomeScreen()
                    1 -> FoodScreen()
                    2 -> TrackerScreen()
                    3 -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Green
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(22.dp)) {
                    Text(
                        "Welcome to Piles Care",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Small daily habits can support better bowel health.",
                        color = Color.White
                    )
                    Spacer(Modifier.height(18.dp))
                    Text(
                        "Take care of yourself 🌿",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        item {
            Text(
                "Your Daily Health Habits",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Dark
            )
        }

        item {
            HabitCard(
                Icons.Default.WaterDrop,
                "Drink enough water",
                "Stay hydrated throughout the day."
            )
        }

        item {
            HabitCard(
                Icons.Default.Spa,
                "Eat fiber-rich foods",
                "Include vegetables, fruits and whole grains."
            )
        }

        item {
            HabitCard(
                Icons.Default.DirectionsWalk,
                "Stay active",
                "Try regular gentle walking."
            )
        }

        item {
            HabitCard(
                Icons.Default.HealthAndSafety,
                "Avoid straining",
                "Do not sit on the toilet for a long time."
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = LightGreen
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Health Reminder",
                        color = Green,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Persistent bleeding, severe pain or worsening symptoms need medical assessment. This app does not replace a doctor."
                    )
                }
            }
        }
    }
}

@Composable
fun HabitCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(LightGreen, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Green)
            }

            Spacer(Modifier.width(14.dp))

            Column {
                Text(title, fontWeight = FontWeight.Bold, color = Dark)
                Spacer(Modifier.height(4.dp))
                Text(description, color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun FoodScreen() {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "Food Guide",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Dark
            )
            Text(
                "Choose fiber-rich foods to support softer stools.",
                color = Color.Gray
            )
        }

        item {
            FoodCard("🥬", "Vegetables", "Spinach, pumpkin, carrots, beans")
        }
        item {
            FoodCard("🍎", "Fruits", "Papaya, guava, orange, pear")
        }
        item {
            FoodCard("🌾", "Whole grains", "Oats, brown rice, whole wheat")
        }
        item {
            FoodCard("💧", "Fluids", "Water and suitable fluids throughout the day")
        }
        item {
            FoodCard("⚠️", "Food tolerance", "Notice which foods worsen your gas or bloating.")
        }
    }
}

@Composable
fun FoodCard(emoji: String, title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 28.sp)
            Spacer(Modifier.width(14.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, color = Dark)
                Text(description, fontSize = 13.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun TrackerScreen() {
    var symptoms by remember { mutableStateOf("") }
    var severity by remember { mutableIntStateOf(3) }
    var saved by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                "Symptom Tracker",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Dark
            )
            Text(
                "Record how you feel each day.",
                color = Color.Gray
            )
        }

        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(Modifier.padding(18.dp)) {
                    Text("Describe your symptoms", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = symptoms,
                        onValueChange = {
                            symptoms = it
                            saved = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Write your symptoms...") },
                        minLines = 3
                    )

                    Spacer(Modifier.height(16.dp))
                    Text("Severity: $severity / 10", fontWeight = FontWeight.Bold)
                    Slider(
                        value = severity.toFloat(),
                        onValueChange = {
                            severity = it.toInt()
                            saved = false
                        },
                        valueRange = 1f..10f
                    )

                    Button(
                        onClick = { saved = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green
                        )
                    ) {
                        Text("Save entry")
                    }

                    if (saved) {
                        Text(
                            "Entry recorded on this screen.",
                            color = Green
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = null,
            tint = Green,
            modifier = Modifier.size(90.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "My Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Dark
        )

        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(Modifier.padding(18.dp)) {
                Text("Piles Care User", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Personal health companion")
                Spacer(Modifier.height(8.dp))
                Text(
                    "More profile features will be added in future updates.",
                    color = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            "Educational information only. Not a substitute for professional medical advice.",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
