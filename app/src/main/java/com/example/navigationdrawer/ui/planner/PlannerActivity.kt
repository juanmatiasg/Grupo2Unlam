package com.example.navigationdrawer.ui.planner

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.ui.favourite.FavouriteViewModel
import com.example.navigationdrawer.ui.planner.ui.theme.NavigationDrawerTheme
import com.example.navigationdrawer.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class PlannerActivity : Fragment() {

    private val mealViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                GoToMeals()
            }
        }
    }


    @Composable
    fun GoToMeals() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .width(100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {findNavController()}) {
                Text(text = "Buscar Comidas")
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        GoToMeals()
    }


    @Composable
    fun EachRow(meals: List<Meals>) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = meals.toString(),
                modifier = Modifier.padding(10.dp),
                fontStyle = FontStyle.Italic
            )
        }
    }

    @Composable
    fun GetData() {
        val state = mealViewModel.getMealsFavoritos().observeAsState().value
        when (state!!.status) {
            Status.SUCCESS -> {
                LazyColumn {
                    items(state.data!!) {
                        val lista = state.data.map {
                            Meals(it.id, it.title, it.image, it.description, "", it.strYoutube)
                        }
                        EachRow(lista)
                    }
                }
            }
            Status.ERROR -> {
                Text(text = "${state.message}")
            }

            Status.LOADING -> {
                CircularProgressIndicator()
            }
        }
    }
}


/*@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}*/

