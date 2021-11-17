package com.example.navigationdrawer.ui.viewImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment
import coil.compose.rememberImagePainter
import com.example.navigationdrawer.R
import com.example.navigationdrawer.databinding.FragmentViewImageBinding
import kotlinx.android.synthetic.main.fragment_view_image.view.*

class ViewImageFragment : DialogFragment() {

    private lateinit var binding: FragmentViewImageBinding

    lateinit var image: ImageView

    fun newInstance(url: String): ViewImageFragment {
        var frag : ViewImageFragment = ViewImageFragment()
        var args: Bundle = Bundle()
        args.putString("urlImage",url)
        frag.arguments = args

        return frag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.AppTheme_Dialog_Custom)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewImageBinding.inflate(layoutInflater,container,false)
        var view: View = binding.root

        view.compose_view.apply {
            // Dispose the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                loadImage("https://toppng.com//public/uploads/preview/pratos-de-comida-115507156144aaok5chqp.png")
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    @Composable
    fun loadImage(url: String){
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(url),
                contentDescription = null,
                modifier = Modifier.size(128.dp).fillMaxWidth().fillMaxHeight(),
            )
        }
    }
}