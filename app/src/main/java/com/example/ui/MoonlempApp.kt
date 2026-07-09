package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.GlassSurfaceNav
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.ImmersiveBlue

@Composable
fun MoonlempApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            Box(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 24.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .shadow(24.dp, RoundedCornerShape(44.dp), spotColor = Color.Black.copy(alpha = 0.5f))
                        .clip(RoundedCornerShape(44.dp))
                        .background(GlassSurfaceNav)
                        .border(1.dp, GlassBorder, RoundedCornerShape(44.dp))
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val isHome = currentDestination?.hierarchy?.any { it.route == Route.Home.route } == true
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            navController.navigate(Route.Home.route) {
                                popUpTo(Route.Home.route) { inclusive = true }
                            }
                        }.padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = if (isHome) ImmersiveBlue else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                        if (isHome) {
                            Box(modifier = Modifier.padding(top = 4.dp).size(4.dp).background(ImmersiveBlue, CircleShape))
                        }
                    }

                    val isNotes = currentDestination?.hierarchy?.any { it.route == Route.Notes.route } == true
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navController.navigate(Route.Notes.route) }.padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Notes",
                            tint = if (isNotes) ImmersiveBlue else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                        if (isNotes) {
                            Box(modifier = Modifier.padding(top = 4.dp).size(4.dp).background(ImmersiveBlue, CircleShape))
                        }
                    }

                    // Floating Center Button
                    Box(modifier = Modifier.width(56.dp)) // Spacer for center button

                    val isPlanner = currentDestination?.hierarchy?.any { it.route == Route.Planner.route } == true
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navController.navigate(Route.Planner.route) }.padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.CalendarToday,
                            contentDescription = "Planner",
                            tint = if (isPlanner) ImmersiveBlue else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                        if (isPlanner) {
                            Box(modifier = Modifier.padding(top = 4.dp).size(4.dp).background(ImmersiveBlue, CircleShape))
                        }
                    }

                    val isSettings = currentDestination?.hierarchy?.any { it.route == Route.Settings.route } == true
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navController.navigate(Route.Settings.route) }.padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = if (isSettings) ImmersiveBlue else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                        if (isSettings) {
                            Box(modifier = Modifier.padding(top = 4.dp).size(4.dp).background(ImmersiveBlue, CircleShape))
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) { // Do not apply padding for floating nav bar overlap, handle scrolling manually if needed, but for fixed layout it's ok
            MoonlempNavHost(navController = navController)
            
            // Just add a spacer to the bottom if needed, or leave it as it overlaps nicely
        }
    }
}
