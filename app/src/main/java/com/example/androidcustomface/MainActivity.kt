package com.example.androidcustomface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcustomface.ui.theme.Violet
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainFun()
        }
    }
}

val colors = listOf(
    "black_" to "Черный",
    "brown_" to "Коричневый",
    "light_" to "Светлый"
)
val hairstyles = listOf(
    "h_first_" to "Короткие",
    "h_second_" to "Длинные",
    "h_third_" to "Кучерявые"
)
val beards = listOf(
    "b_none" to "Нет",
    "b_first" to "Борода",
    "b_second" to "Усы"
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainFun() {
    val color = remember() { mutableStateOf(colors[0]) }
    val hairstyle = remember() { mutableStateOf(hairstyles[0]) }
    val beard = remember() { mutableStateOf(beards[0]) }
    val style = color.value.first + hairstyle.value.first + beard.value.first
    val openDropMenu = remember { mutableStateListOf(false, false, false) }
    val mContext = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
//            TopBar(
//                setDefault = {
//                    color.value = colors[0]
//                    hairstyle.value = hairstyles[0]
//                    beard.value = beards[0]
//                },
//                setRandom = {
//                    color.value = colors.random()
//                    hairstyle.value = hairstyles.random()
//                    beard.value = beards.random()
//                }
//            )
            TopBar(color, hairstyle, beard)
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
//            val color = remember() { mutableStateOf(colors[0]) }
//            val hairstyle = remember() { mutableStateOf(hairstyles[0]) }
//            val beard = remember() { mutableStateOf(beards[0]) }
//            val style = color.value.first+hairstyle.value.first+beard.value.first
//            val openDropMenu = remember { mutableStateListOf(false, false, false) }
            Image(
                painter = painterResource(
                    mContext.resources
                        .getIdentifier(style, "drawable", mContext.packageName)
                ),
                contentDescription = "Image",
                modifier = Modifier
                    .padding(20.dp)
            )
            Text(
                text = "Цвет волос:",
                fontSize = 24.sp
            )
            ExposedDropdownMenuBox(
                expanded = openDropMenu[0],
                onExpandedChange = { openDropMenu[0] = !openDropMenu[0] }
            ) {
                TextField(
                    value = color.value.second,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = openDropMenu[0])
                    },
                    modifier = Modifier
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = openDropMenu[0],
                    onDismissRequest = { openDropMenu[0] = false },
                ) {
                    colors.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.second) },
                            onClick = {
                                color.value = it
                                openDropMenu[0] = false
                            })
                    }
                }
            }
            Text(
                text = "Прическа:",
                fontSize = 24.sp
            )
            ExposedDropdownMenuBox(
                expanded = openDropMenu[1],
                onExpandedChange = { openDropMenu[1] = !openDropMenu[1] }
            ) {
                TextField(
                    value = hairstyle.value.second,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = openDropMenu[0])
                    },
                    modifier = Modifier
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = openDropMenu[1],
                    onDismissRequest = { openDropMenu[1] = false },
                ) {
                    hairstyles.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.second) },
                            onClick = {
                                hairstyle.value = it
                                openDropMenu[1] = false
                            })
                    }
                }
            }
            Text(
                text = "Борода:",
                fontSize = 24.sp
            )
            ExposedDropdownMenuBox(
                expanded = openDropMenu[2],
                onExpandedChange = { openDropMenu[2] = !openDropMenu[2] }
            ) {
                TextField(
                    value = beard.value.second,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = openDropMenu[2])
                    },
                    modifier = Modifier
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = openDropMenu[2],
                    onDismissRequest = { openDropMenu[2] = false },
                ) {
                    beards.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.second) },
                            onClick = {
                                beard.value = it
                                openDropMenu[2] = false
                            })
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(
    color: MutableState<Pair<String, String>>,
    hairstyle: MutableState<Pair<String, String>>,
    beard: MutableState<Pair<String, String>>,
) {
    val openMenu = remember { mutableStateOf(false) }
    val optoinsMenu = listOf(
        "Сброс",
        "Случайно"
    )
    TopAppBar(
        colors = topAppBarColors(
            containerColor = Violet,
            titleContentColor = Color.White
        ),
        title = {
            Row {
                IconButton(
                    onClick = {
                        openMenu.value = true
                    },
                    modifier = Modifier
                        .padding(end = 10.dp, start = 5.dp),
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                }
                DropdownMenu(
                    expanded = openMenu.value,
                    onDismissRequest = { openMenu.value = false },
                    modifier = Modifier
                        .background(Color.White),
                ) {
                    DropdownMenuItem(
                        onClick = {
                            color.value = colors[0]
                            hairstyle.value = hairstyles[0]
                            beard.value = beards[0]
                            openMenu.value = false
                        },
                        text = { Text(optoinsMenu[0]) }
                    )
                    DropdownMenuItem(
                        onClick = {
                            color.value = colors.random()
                            hairstyle.value = hairstyles.random()
                            beard.value = beards.random()
                            openMenu.value = false
                        },
                        text = { Text(optoinsMenu[1]) }
                    )
                }
                Text(text = stringResource(R.string.app_name))
                Spacer(
                    modifier = Modifier
                        .weight(1F)
                )

                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Exit",
                    modifier = Modifier
                        .padding(end = 10.dp, start = 5.dp)
                        .clickable {
                            exitProcess(-1)
                        }
                )
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(
    setDefault: () -> Unit,
    setRandom: () -> Unit,
) {
    val openMenu = remember { mutableStateOf(false) }
    val optoinsMenu = listOf(
        "Сброс",
        "Случайно"
    )
    TopAppBar(
        colors = topAppBarColors(
            containerColor = Violet,
            titleContentColor = Color.White
        ),
        title = {
            Row {
                IconButton(
                    onClick = {
                        openMenu.value = true
                    },
                    modifier = Modifier
                        .padding(end = 10.dp, start = 5.dp),
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                }
                DropdownMenu(
                    expanded = openMenu.value,
                    onDismissRequest = { openMenu.value = false },
                    modifier = Modifier
                        .background(Color.White),
                ) {
                    DropdownMenuItem(
                        onClick = {
                            setDefault
                            openMenu.value = false
                        },
                        text = { Text(optoinsMenu[0]) }
                    )
                    DropdownMenuItem(
                        onClick = {
                            setRandom
                            openMenu.value = false
                        },
                        text = { Text(optoinsMenu[1]) }
                    )
                }
                Text(text = stringResource(R.string.app_name))
                Spacer(
                    modifier = Modifier
                        .weight(1F)
                )

                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Exit",
                    modifier = Modifier
                        .padding(end = 10.dp, start = 5.dp)
                        .clickable {
                            exitProcess(-1)
                        }
                )
            }
        }
    )
}