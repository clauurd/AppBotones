package com.example.appbotones

import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appbotones.ui.theme.AppBotonesTheme
import kotlinx.coroutines.delay
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBotonesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Fila superior
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Blue)
                            ) {
                                PermanentIconExample()
                            }
                        }

                        // Secciones debajo de la fila superior
                        Row(
                            modifier = Modifier.fillMaxSize()
                                .weight(1f)
                        ) {
                            // Sección 1
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Red)
                            ) {
                                BotonCircular()
                            }

                            // Sección 2
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Green)
                            ) {
                                CheckBoxExample()
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxSize()
                                .weight(1f)
                        ) {
                            // Sección 3
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Yellow)
                            ) {
                                SwitchWithIconExample()
                            }

                            // Sección 4
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Cyan)
                            ) {
                                ChangeImageOnButtonClick()
                            }
                        }
                    }
                }

                }
            }
        }
    }

@Composable
fun BotonCircular(){
    var presionado by remember { mutableStateOf(0) }
    Button(onClick = {presionado++}
    ) {

        Text(text = "Veces presionado: $presionado")
        CircularProgressBarExample()
    }
}

@Composable
fun CircularProgressBarExample() {
    var isLoading by remember { mutableStateOf(true) }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }

        LaunchedEffect(isLoading) {
            delay(5000) // Espera durante 5 segundos
            isLoading = false // Cambia el estado para dejar de mostrar el CircularProgressIndicator
        }
    } else {
        // Aquí puedes mostrar el contenido después de que termine la espera
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contenido después de 5 segundos",

                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun CheckBoxExample() {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { newCheckedState ->
                isChecked = newCheckedState
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isChecked == true){
            Text("CheckBox está marcado: $isChecked")
        }

    }
}

@Composable
fun PermanentIconExample() {
    Icon(Icons.Rounded.Menu, contentDescription = "Localized description")
}

@Composable
fun SwitchWithIconExample() {
    Modifier.fillMaxSize()
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },

        thumbContent = if (checked) {
            {
                SingleSelectionRadioButton()
            }

        } else {
            null
        },

    )
}
enum class Options { Option1, Option2, Option3 }

@Composable
fun SingleSelectionRadioButton() {
    var selectedOption by remember { mutableStateOf(Options.Option1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioButton(
            selected = selectedOption == Options.Option1,
            onClick = { selectedOption = Options.Option1 }
        )
        Text("Opción 1")

        RadioButton(
            selected = selectedOption == Options.Option2,
            onClick = { selectedOption = Options.Option2 }
        )
        Text("Opción 2")

        RadioButton(
            selected = selectedOption == Options.Option3,
            onClick = { selectedOption = Options.Option3 }
        )
        Text("Opción 3")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Opción seleccionada: $selectedOption")
    }
}
@Composable
fun ChangeImageOnButtonClick() {
    var imageIndex by remember { mutableStateOf(0) }
    val images = listOf(
        R.drawable.image1,  // Reemplaza con tus recursos de imagen
        R.drawable.image2,
        R.drawable.image3
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = images[imageIndex]),
            contentDescription = "Imagen"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                imageIndex = (imageIndex + 1) % images.size
            }
        ) {
            Text("Cambiar Imagen")
        }
    }
}