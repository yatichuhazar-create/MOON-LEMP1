package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        
        // Glow Overlays (simulated with large blurred circles if we had blur, but basic radial gradients or solid blurred circles are tricky. We'll skip for simple implementation, or just use backgrounds)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 40.dp, bottom = 120.dp) // extra bottom padding for floating nav
        ) {
            // Header Section
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onBackground)) {
                                append("MOON")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic, color = ImmersiveBlueLight)) {
                                append("LEMP")
                            }
                        },
                        fontSize = 32.sp,
                        letterSpacing = (-1).sp
                    )
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        // Theme Toggle Button
                        val isDarkTheme = androidx.compose.foundation.isSystemInDarkTheme()
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surface)
                                .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                                .clickable {
                                    if (isDarkTheme) {
                                        androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO)
                                    } else {
                                        androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            val icon = if (isDarkTheme) Icons.Filled.WbSunny else Icons.Filled.NightsStay
                            Icon(icon, contentDescription = "Toggle Theme", tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(20.dp))
                        }
                        // Focus Button
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(22.dp))
                                .background(ImmersiveBlue.copy(alpha = 0.1f))
                                .border(1.dp, ImmersiveBlue.copy(alpha = 0.3f), RoundedCornerShape(22.dp))
                                .shadow(20.dp, RoundedCornerShape(22.dp), spotColor = ImmersiveBlue.copy(alpha = 0.3f))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.SelfImprovement, contentDescription = "Focus", tint = ImmersiveBlueLight)
                        }
                    }
                }
            }

            // Welcome Message
            item {
                Column(modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 24.dp)) {
                    Text("Good evening,", fontSize = 24.sp, fontWeight = FontWeight.Light, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                    Text("Tonmoy", fontSize = 32.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onBackground)
                }
            }

            // Quick Action Capsules
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCapsule(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Filled.Timer,
                        iconBg = Color(0xFFF97316).copy(alpha = 0.2f), // Orange
                        iconTint = Color(0xFFFB923C),
                        subtitle = "TIMER",
                        title = "Focus",
                        onClick = { navController.navigate("planner") }
                    )
                }
            }

            // Today's Tasks Glass Card
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(GlassSurface)
                        .border(1.dp, GlassBorder, RoundedCornerShape(32.dp))
                        .padding(20.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("TODAY'S SCHEDULE", fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp, color = ImmersiveBlueLight)
                        Box(modifier = Modifier.clip(CircleShape).background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Text("4 Pending", fontSize = 10.sp, color = MaterialTheme.colorScheme.onBackground)
                        }
                    }

                    TaskItem("Final Prototype Review", "09:30 AM — Design Lab", ImmersiveBlue, false)
                    Spacer(modifier = Modifier.height(12.dp))
                    TaskItem("Gemini API Integration", "11:00 AM — Development", ImmersiveIndigo, false)
                    Spacer(modifier = Modifier.height(12.dp))
                    TaskItem("Morning Reflection", "Completed at 08:15 AM", Color.Gray, true)

                    // Habit Tracking Micro-Visual
                    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), modifier = Modifier.padding(vertical = 16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text("HYDRATION", fontSize = 10.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))
                            Text("1.2 / 2.5L", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(3) { Box(modifier = Modifier.width(6.dp).height(24.dp).clip(CircleShape).background(ImmersiveBlue)) }
                            repeat(2) { Box(modifier = Modifier.width(6.dp).height(24.dp).clip(CircleShape).background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))) }
                        }
                    }
                }
            }

            // Bottom Stats Strip
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(modifier = Modifier.weight(1f), label = "GPA", value = "9.8")
                    StatCard(modifier = Modifier.weight(1f), label = "Exam", value = "12d")
                    StatCard(modifier = Modifier.weight(1f), label = "Focus", value = "4.2h")
                }
            }
        }
    }
}

@Composable
fun QuickActionCapsule(modifier: Modifier = Modifier, icon: ImageVector, iconBg: Color, iconTint: Color, subtitle: String, title: String, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(GlassSurface)
            .border(1.dp, GlassBorder, RoundedCornerShape(32.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(16.dp)).background(iconBg), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = iconTint)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(subtitle, fontSize = 10.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f), letterSpacing = 1.sp)
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun TaskItem(title: String, subtitle: String, color: Color, isCompleted: Boolean) {
    val alpha = if (isCompleted) 0.5f else 1f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f))
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(12.dp)
            .alpha(alpha),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(8.dp).height(32.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title, 
                fontSize = 14.sp, 
                fontWeight = FontWeight.Medium, 
                color = MaterialTheme.colorScheme.onBackground,
                textDecoration = if (isCompleted) androidx.compose.ui.text.style.TextDecoration.LineThrough else null
            )
            Text(subtitle, fontSize = 10.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (isCompleted) ImmersiveBlue.copy(alpha = 0.4f) else Color.Transparent)
                .border(2.dp, if (isCompleted) Color.Transparent else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) Icon(Icons.Filled.Check, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, label: String, value: String) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f))
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), RoundedCornerShape(24.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
    }
}

