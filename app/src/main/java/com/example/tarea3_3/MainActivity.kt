package com.example.tarea3_3

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tarea3_3.ui.theme.Tarea33Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tarea33Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}
var partidas = 5
var j1 = 0
var mensaje = ""
var maquina = Random.nextInt(1,4)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context= LocalContext.current
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(0.dp,20.dp,0.dp,20.dp)){
        Row (verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceEvenly){
            Image(painter = painterResource(id = R.drawable.piedra),
                contentDescription = "Piedra máquina", Modifier.size(100.dp))
            Image(painter = painterResource(id = R.drawable.papel),
                contentDescription = "Papel máquina", Modifier.size(100.dp))
            Image(painter = painterResource(id = R.drawable.tijeras),
                contentDescription = "Tijeras máquina", Modifier.size(100.dp))
        }
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            if (j1!= 0){
                partidas--
                mostrarImagenes()
            }
        }
        Row (verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceEvenly){
            Button(onClick = {
                j1 = 1
                Ganador(context)
            },modifier.width(100.dp)) {
                Image(painter = painterResource(id = R.drawable.piedra), contentDescription = "Piedra jugador")
            }
            Button(onClick = {
                j1 = 2
                Ganador(context)
            },modifier.width(100.dp)) {
                Image(painter = painterResource(id = R.drawable.papel), contentDescription = "Papel jugador")
            }
            Button(onClick = {
                j1 = 3
                Ganador(context)
            },modifier.width(100.dp)) {
                Image(painter = painterResource(id = R.drawable.tijeras), contentDescription = "Tijeras jugador")
            }
        }
    }
}
@Composable
fun mostrarImagenes() {
    if (j1 == 1){
        Image(painter = painterResource(id = R.drawable.piedra), contentDescription = "Elección 1")
    }else if (j1 == 2){
        Image(painter = painterResource(id = R.drawable.papel), contentDescription = "Elección 2")
    }else
        Image(painter = painterResource(id = R.drawable.piedra), contentDescription = "Elección 3")
    if (maquina == 1){
        Image(painter = painterResource(id = R.drawable.piedra), contentDescription = "Máquina 1")
    }
}
fun Ganador(context : Context){
    Log.d("random", maquina.toString())
    if (j1.equals(maquina)){
        mensaje = "Empate"
    }else if ((j1 == 1 && maquina == 2) || (j1 == 2 && maquina == 3) || (j1 == 3 && maquina == 1)){
        mensaje = "Has perdido"
    }else if ((j1 == 1 && maquina == 3) || (j1 == 2 && maquina == 1) || (j1 == 3 && maquina == 2))
        mensaje = "Has ganado"
    Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tarea33Theme {
        Greeting("Android")
    }
}