package com.example.navigationdrawer.ui.planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentPlannerBinding
import com.example.navigationdrawer.ui.favourite.FavouriteViewModel
import com.example.navigationdrawer.vo.Resource
import com.example.navigationdrawer.vo.Status
import kotlinx.android.synthetic.main.fragment_planner.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class PlannerFragment : Fragment() {

    private var _binding: FragmentPlannerBinding? = null
    private val binding get() = _binding!!

    private val mealViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlannerBinding.inflate(inflater, container, false)
        val view = binding.root
        view.compose_view.apply {
            // Dispose the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                GoToFindMeals()

            }
        }
        return view
    }

    @Composable
    fun GoToFindMeals() {
        Column {
            Button(
                onClick = { findNavController().navigate(R.id.action_plannerFragment_to_nav_mealFragment) },
                modifier = Modifier
                    .align(alignment = CenterHorizontally)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "Searching"
                )
                Text("Buscar Comidas")
            }
            Text(
                "Desayuno", modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            BarkHomeContent()
        }

    }

    @Composable
    fun RecylerViewDesayuno() {
        Column {
            Text(
                "Desayuno", modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            /*val lista = mealViewModel.getMealsFavoritos().observeAsState()
                when(lista.status){
                    Status.LOADING -> {
                        CircularProgressIndicator()
                    }
                    Status.SUCCESS -> {
                        LazyColumn {
                            items(it.data!!){
                                val lista = it.data!!.map {
                                    Meals(it.id,it.title,it.image,it.description,"",it.strYoutube)
                                }
                                EachRow(lista)
                            }
                        }
                    }
                    Status.ERROR -> {
                        // Text(text = "${state.message}")
                    }
                }
            })
                /*when (result!!.status) {
                    Status.LOADING -> {
                        CircularProgressIndicator()
                    }
                    Status.SUCCESS -> {
                        LazyColumn {
                            items(result.data!!.size){
                                val lista = result.data.map {
                                    Meals(it.id,it.title,it.image,it.description,"",it.strYoutube)
                                }
                                EachRow(lista)
                            }
                        }
                    }
                    Status.ERROR -> {
                        // Text(text = "${state.message}")
                    }
                }*/
            }*/


        }
    }

    @Composable
    fun BarkHomeContent() {

        /*val lista = mealViewModel.getMealsFavoritos().value!!.data!!.map {
            Meals(it.id,it.title,it.image,it.description,"",it.strYoutube)
        }*/

        val puppyList = listOf<Meals>(
            Meals("1", "Manzana", "Imagen 1 ", "desc1", "", ""),
            Meals("2", "Mandarina", "Imagen 2 ", "desc1", "", ""),
            Meals("3", "Pizza", "Imagen 2 ", "desc1", "", ""),
            Meals("4", "Fideos", "Imagen 2 ", "desc1", "", ""),
            Meals("5", "Empanadas", "Imagen 2 ", "desc1", "", ""),
        )

        val puppies = remember { puppyList }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = puppies,
                itemContent = {
                    PuppyListItem(puppy = it)
                })
        }
    }

    @Composable
    fun PuppyListItem(puppy: Meals) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Column {
                Text(
                    text = "${puppy.id}",
                    modifier = Modifier.padding(10.dp),
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "${puppy.title}",
                    modifier = Modifier.padding(10.dp),
                    fontStyle = FontStyle.Italic
                )
            }

        }
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
                text = "${meals}",
                modifier = Modifier.padding(10.dp),
                fontStyle = FontStyle.Italic
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}