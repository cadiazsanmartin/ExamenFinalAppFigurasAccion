package com.example.appfigurasaccion.adapter

import android.app.Fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.example.appfigurasaccion.MainActivity
import com.example.appfigurasaccion.R
import com.example.appfigurasaccion.data.FiguraDetalle
import com.example.appfigurasaccion.databinding.FragmentFiguraDetailBinding
import com.example.appfigurasaccion.viewmodel.FiguraViewModel

class FiguraDetailFragment : Fragment(R.layout.fragment_figura_detail) {

    private lateinit var binding: FragmentFiguraDetailBinding
    private lateinit var figuraViewModel: FiguraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiguraDetailBinding.inflate(inflater, container, false)

        val figuraId = arguments?.getInt("figuraId") ?: return null

        figuraViewModel = (activity as MainActivity).figuraViewModel
        figuraViewModel.getFiguraDetalle(figuraId)

        figuraViewModel.figuraDetalle.observe(viewLifecycleOwner) { figura ->
            binding.figuraName.text = figura.nombre
            binding.figuraDescripcion.text = figura.descripcion
            binding.figuraFechaCreacion.text = "Fecha de Creación: ${figura.fechaCreacion}"
            binding.figuraOrigen.text = "Origen: ${figura.origen}"
            binding.figuraPrecio.text = "Precio: $${figura.precio}"
            binding.figuraColores.text = "Colores: ${figura.colores.joinToString(", ")}"

            Glide.with(requireContext()).load(figura.logo).into(binding.figuraImage)

            binding.sendEmailButton.setOnClickListener {
                sendEmail(figura)
            }
        }

        return binding.root
    }

    //Configuración de correo
    private fun enviarEmail(figura: FiguraDetalle) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:figuras@gmail.com"))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Información sobre mi figura - ${figura.nombre}")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Buen día,\n Solicito más información sobre esta figura - ${figura.nombre}. Además, quiero suscribirme a información diaria y las últimas noticias.\n Quedo atenta a la información, muchas gracias.")
        startActivity(Intent.createChooser(emailIntent, "Enviar correo"))
    }

    }

    companion object {
        fun newInstance(figuraId: Int): FiguraDetailFragment {
            val fragment = FiguraDetailFragment()
            val args = Bundle().apply {
                putInt("figuraId", figuraId)
            }
            fragment.arguments = args
            return fragment
        }
    }
}