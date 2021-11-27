package com.example.criptomonedas_tania.ui.view.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criptomonedas_tania.data.db.config.Criptomoneda_app.Companion.prefs

import com.example.criptomonedas_tania.databinding.FragmentCriptomonedaBinding
import com.example.criptomonedas_tania.ui.view.adapter.CustomAdapter
import com.example.criptomonedas_tania.ui.view.interfaces.InterfaceCripto
import com.example.criptomonedas_tania.ui.viewmodel.CriptomonedaViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CriptomonedaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CriptomonedaFragment : Fragment() {

    private var _binding: FragmentCriptomonedaBinding?=null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var activity: Activity
    lateinit var interfaceCrito: InterfaceCripto
    private lateinit var criptoViewModel: CriptomonedaViewModel
    private lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCriptomonedaBinding.inflate(inflater,container,false)
        this.criptoViewModel = ViewModelProvider(this).get(CriptomonedaViewModel::class.java)
        initRecyclerView()


        return binding.root
    }

    private fun initRecyclerView() {


        criptoViewModel.criptomonedas.observe(viewLifecycleOwner, Observer {

            Log.d("criptomoneda_observe",it.toString())
            adapter = CustomAdapter(it, requireContext())
            binding.recyclerCriptomoneda.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerCriptomoneda.adapter=adapter

            adapter.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v: View?) {
                    val id_cripto:String = it.get(binding.recyclerCriptomoneda.getChildAdapterPosition(v!!)).id

                    prefs.idCripto = id_cripto

                    Log.d("id", id_cripto.toString())
                    interfaceCrito.viewCriptomonedaDetail()
                }
            }
            )
        })
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
        interfaceCrito = activity as InterfaceCripto
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CriptomonedaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CriptomonedaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}