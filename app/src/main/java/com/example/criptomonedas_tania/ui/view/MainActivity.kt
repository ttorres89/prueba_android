package com.example.criptomonedas_tania.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criptomonedas_tania.R
import com.example.criptomonedas_tania.databinding.ActivityMainBinding
import com.example.criptomonedas_tania.ui.view.fragments.CriptomonedaDetailFragment
import com.example.criptomonedas_tania.ui.view.fragments.CriptomonedaFragment
import com.example.criptomonedas_tania.ui.view.interfaces.InterfaceCripto
import com.example.criptomonedas_tania.ui.viewmodel.CriptomonedaViewModel

class MainActivity : AppCompatActivity(), InterfaceCripto {

    private lateinit var  viewModel: CriptomonedaViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.viewModel = ViewModelProvider(this).get(CriptomonedaViewModel::class.java)
        setContentView(binding.root)
        viewCriptomoneda()
    }


    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contentCriptoFragmente, fragment)
        fragmentTransaction.commit()
    }

    override fun viewCriptomoneda() {
        replaceFragment(CriptomonedaFragment())
    }

    override fun viewCriptomonedaDetail() {
        replaceFragment(CriptomonedaDetailFragment())
    }
}