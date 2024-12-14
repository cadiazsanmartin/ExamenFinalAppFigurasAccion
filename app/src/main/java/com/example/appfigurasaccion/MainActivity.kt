package com.example.appfigurasaccion

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfigurasaccion.adapter.FiguraAdapter
import com.example.appfigurasaccion.viewmodel.FiguraViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val figuraViewModel: FiguraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView en orientación horizontal
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        figuraViewModel.getFiguras()

        figuraViewModel.figuras.observe(this, Observer { figuras ->
            val adapter = FiguraAdapter(figuras) { figura ->
                // Acción al hacer clic en una figura
                val action = MainFragmentDirections.actionMainFragmentToFiguraDetailFragment(figura.id)
                findNavController().navigate(action)
            }
            recyclerView.adapter = adapter
        })

        figuraViewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}