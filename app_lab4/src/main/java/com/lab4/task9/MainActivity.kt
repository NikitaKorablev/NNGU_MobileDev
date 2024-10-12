package com.lab4.task9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.layout.fillMaxWidth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuApp(this::finish)
        }
    }
}

@Composable
fun MenuApp(onClose: () -> Unit) {
    var selectedItem by remember { mutableStateOf("Выберите элемент меню") }
    val density = LocalDensity.current // Получаем текущую плотность
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed, density)
    )
    val coroutineScope = rememberCoroutineScope()

    // Состояние для выпадающего меню
    var expanded by remember { mutableStateOf(false) }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Верхнее меню") },
                actions = {
                    // Кнопка для открытия выпадающего меню
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Меню")
                    }
                    // Выпадающее меню
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            selectedItem = "Элемент 1"
                            expanded = false
                        }) {
                            Text("Элемент 1")
                        }
                        DropdownMenuItem(onClick = {
                            selectedItem = "Элемент 2"
                            expanded = false
                        }) {
                            Text("Элемент 2")
                        }
                        DropdownMenuItem(onClick = {
                            selectedItem = "Элемент 3"
                            expanded = false
                        }) {
                            Text("Элемент 3")
                        }
                    }
                }
            )
        },
        sheetContent = {
            BottomSheetMenu { item ->
                selectedItem = item
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), // Используем content padding из BottomSheetScaffold
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = selectedItem, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }) {
                Text(text = "Открыть нижнее меню")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                onClose()
            }) {
                Text(text = "Main menu")
            }
        }
    }
}

@Composable
fun BottomSheetMenu(onMenuItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Нижнее меню", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        Button(onClick = { onMenuItemClick("Элемент 4") }) {
            Text("Элемент 4")
        }
        Button(onClick = { onMenuItemClick("Элемент 5") }) {
            Text("Элемент 5")
        }
        Button(onClick = { onMenuItemClick("Элемент 6") }) {
            Text("Элемент 6")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MenuApp(this::fini)
//}
