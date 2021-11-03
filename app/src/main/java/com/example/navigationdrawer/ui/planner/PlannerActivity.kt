package com.example.navigationdrawer.ui.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationdrawer.R
import com.example.navigationdrawer.ui.planner.ui.theme.NavigationDrawerTheme

class PlannerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ButtonSearchMeals()
                RecyclerViewDesayunoMeals()
                RecyclerViewDesayunoMeals()
                RecyclerViewAlmuerzoMeals()
            }
        }
    }
}

@Composable
fun ButtonSearchMeals() {
     Row{
        Button(onClick = { /*TODO*/ }) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = ""
                )
                Text(text = "Ver todas las comidas")
            }
        }
    }
}

@Composable
fun RecyclerViewDesayunoMeals() {
    Row() {
        Text(
            text = "Desayuno",
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "Limpiar Todo", modifier = Modifier.padding(20.dp)
        )
    }
    Row {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Agregar Recetas",
                    style = MaterialTheme.typography.h6,
                    fontSize = 8.sp
                )
            }
        }
    }
}

@Composable
fun RecyclerViewAlmuerzoMeals() {
    Row {
        Text(
            text = "Almuerzo",
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "Limpiar Todo", modifier = Modifier.padding(20.dp)
        )
    }

    Row {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.h6
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Agregar Recetas",
                    style = MaterialTheme.typography.h6,
                    fontSize = 8.sp
                )
            }
        }
    }
}

@Composable
fun RecyclerViewMeriendaMeals() {
    Row {
        Text(
            text = "Merienda",
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "Limpiar Todo",
            modifier = Modifier.padding(20.dp)
        )
    }
    Row {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
                    .width(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "3",
                    style = MaterialTheme.typography.h6
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationDrawerTheme {
        Column {
            ButtonSearchMeals()
            RecyclerViewDesayunoMeals()
            RecyclerViewAlmuerzoMeals()
            RecyclerViewMeriendaMeals()
        }

    }
}