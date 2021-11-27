package com.example.criptomonedas_tania.ui.view.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.criptomonedas_tania.R
import com.example.criptomonedas_tania.data.db.config.Criptomoneda_app.Companion.prefs
import com.example.criptomonedas_tania.databinding.FragmentCriptomonedaDetailBinding
import com.example.criptomonedas_tania.ui.view.interfaces.InterfaceCripto
import com.example.criptomonedas_tania.ui.viewmodel.CriptomonedaViewModel
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CriptomonedaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CriptomonedaDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentCriptomonedaDetailBinding?=null
    private val binding get() = _binding!!
    lateinit var activity: Activity
    lateinit var icomunicateCripto: InterfaceCripto
    private lateinit var criptoViewModel: CriptomonedaViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCriptomonedaDetailBinding.inflate(inflater,container,false)
        this.criptoViewModel = ViewModelProvider(this).get(CriptomonedaViewModel::class.java)
        initBook()
        inicializarBtnMenuPrincipal()
        initsendEmail()

        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initBook() {

        this.criptoViewModel.detail_criptomoneda.observe(viewLifecycleOwner, Observer {

            val formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = LocalDate.parse(it.price_date, formatDate)
            val timestamp = LocalDate.parse(it.price_timestamp, formatDate)

            binding.nameDetail.setText(it.name)
            binding.symbolDetail.setText(it.symbol)
            binding.statusDetail.setText(it.status)
            binding.priceDetail.setText(it.price.toString())
            binding.priceDateDetail.setText(date.toString())
            binding.priceTimestampDateDetail.setText(it.price_timestamp)
            binding.rankDetail.setText(it.rank.toString())

            Picasso.with(context).load(it.logo_url).into(binding.itemImageDetail);


        })
    }

    private fun initsendEmail() {
        binding.buttonInfo.setOnClickListener {

            val recipient = "info@cryptoinvest.cl"
            val subject =  " Solicito información sobre esta criptomoneda ID: " +  prefs.idCripto

            val message = "Hola\n\n" +
                    "Quisiera pedir información sobre esta moneda: " + binding.nameDetail.text.toString() +", me gustaría que me contactaran a\n" +
                    "este correo o al siguiente número ______________\n\n" +
                    "Quedo atento."

            //method call for email intent with these inputs as parameters
            sendEmail(recipient, subject, message)
        }
    }

    private fun inicializarBtnMenuPrincipal() {

        binding.btnAtras.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                icomunicateCripto.viewCriptomoneda()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
        icomunicateCripto = activity as InterfaceCripto
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {

        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))

        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)

        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){

            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CriptomonedaDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CriptomonedaDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}