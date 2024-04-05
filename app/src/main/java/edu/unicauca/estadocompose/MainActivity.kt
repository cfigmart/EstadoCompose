package edu.unicauca.estadocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.estadocompose.ui.theme.EstadoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstadoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaPrincipal()
                }
            }
        }
    }
}

@Composable
fun ContadorAgua(
    cont: Int,
    onClick: ()->Unit,
    onClear: ()->Unit,
    modifier: Modifier = Modifier) {
    Column (modifier.padding(16.dp)){
        if (cont > 0) {
            Text(text = "Has bebido ${cont} vasos de agua",
                Modifier.padding(8.dp))
        }
        Row {
            OutlinedButton(
                onClick = onClick,
                enabled = cont < 10,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Agregar Vaso")
            }
            if(cont > 0){
                OutlinedButton(
                    onClick = onClear,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Limpiar")
                }
            }
        }
    }
}
@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier){
     var cont by rememberSaveable {
        mutableStateOf(0)
    }
    var mostrarTarea by rememberSaveable {
        mutableStateOf(true)
    }
    Column {
        ContadorAgua(
            cont = cont,
            onClick = { cont++ },
            onClear = {cont=0}
        )
        if(cont>0 && mostrarTarea){
            TareaItem(taskName = "Beber más agua", onClose = { mostrarTarea=false})
        }


    }

}

@Preview
@Composable
fun ContadorAguaPreview() {
    PantallaPrincipal()
}











