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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
val eleccion = listOf (
    R.drawable.piedra, // 0 - Rock
    R.drawable.papel, // 1 - Paper
    R.drawable.tijeras, // 2 - Scissors

)
var ronda = 0 // Variable que define la ronda en la que estamos.
var p1 = 0 // Puntuación del jugador.
var p2 = 0 // Puntuación de la máquina.
var j1 = 0 // Elección del jugador.
var mensaje = "" // Variable que mostrará el resultado de cada ronda.
var maquina =
    Random.nextInt(1, 4) // Variable utilizada para generar la elección aleatoria de la máquina.

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 20.dp)
    ) {
        // Fila con las opciones de la máquina.
        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(
                painter = painterResource(id = R.drawable.piedra),
                contentDescription = "Piedra máquina", Modifier.size(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.papel),
                contentDescription = "Papel máquina", Modifier.size(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.tijeras),
                contentDescription = "Tijeras máquina", Modifier.size(100.dp)
            )
        }
        // Columna que contiene la puntuación de la partida(O eso debería xd),
        // y la opción seleccionada por la máquina a la izquierda y la elegida por el usuario a la derecha.
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier.align(Alignment.CenterHorizontally)) {
                // Sé que no se cambia pero la intención es lo que cuenta(Insertar jamón ibérico <3).
                Text(
                    text = "$p1-$p2",
                    modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                    fontSize = 50.sp,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                mostrarImagenes()
            }
        }
        // Fila con las opciones que tiene el usuario a elegir.
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                j1 = 1
                Ronda(context)
            }, modifier.width(100.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.piedra),
                    contentDescription = "Piedra jugador"
                )
            }
            Button(onClick = {
                j1 = 2
                Ronda(context)
            }, modifier.width(100.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.papel),
                    contentDescription = "Papel jugador"
                )
            }
            Button(onClick = {
                j1 = 3
                Ronda(context)
            }, modifier.width(100.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.tijeras),
                    contentDescription = "Tijeras jugador"
                )
            }
        }
    }
}

/**
 * Este método debería insertar las opciones seleccionadas por cada parte en cada ronda.
 * Pero no sé por qué no funciona, si me pudieras decir cómo es te lo agradecería mucho.
 */
@Composable
fun mostrarImagenes() {
    Image(painter = painterResource(id = eleccion[maquina]), contentDescription = "Elección máquina")
    Image(painter = painterResource(id = eleccion[p1]), contentDescription = "Elección jugador")
}

/**
 * Método que se encarga de calcular el resultado de cada ronda, mostrando un mensaje con el ganador de la misma.
 */
fun Ronda(context: Context) {
    if (ronda < 5 && !compruebaFin()) {
        ronda++
        maquina = Random.nextInt(1, 4)
        Log.d("random", maquina.toString())
        if (j1 == maquina) {
            mensaje = "Empate"
        } else if ((j1 == 1 && maquina == 2) || (j1 == 2 && maquina == 3) || (j1 == 3 && maquina == 1)) {
            mensaje = "Has perdido"
            p2++
        } else if ((j1 == 1 && maquina == 3) || (j1 == 2 && maquina == 1) || (j1 == 3 && maquina == 2)) {
            mensaje = "Has ganado"
            p1++
        }
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    } else
        ganador(context)
}

/**
 * Método que comprueba si la partida ha terminado.
 * No funciona pero yo el código lo veo bien, no sé donde está el problema.
 */
fun compruebaFin(): Boolean {
    var fin = false
    if (ronda == 3 && (p1 == 3 || p2 == 3)) {
        fin = true
    } else if (ronda == 4 && ((p1 == 2 && p2 == 0) || (p1 == 0 && p2 == 2))) {
        fin = true
    }
    return fin
}

/**
 * Este método muestra por pantalla un mensaje diciendo cómo ha terminado la partida(Si el método anterior funcionase claro...).
 * David, somos lo suficientemente inteligentes para saber el resultado. Este método no es necesario <3.
 */
fun ganador(context: Context) {
    val toast: Toast = Toast.makeText(context, "", Toast.LENGTH_LONG)
    if (p1 == p2) {
        toast.setText("La partida ha terminado en empate")
    } else if (p1 > p2) {
        toast.setText("Has ganado la partida")
    } else
        toast.setText("Has perdido la partida")
    toast.show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tarea33Theme {
        Greeting("Android")
    }
}