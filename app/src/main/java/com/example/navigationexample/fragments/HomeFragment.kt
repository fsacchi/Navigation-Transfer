package com.example.navigationexample.fragments

import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.R
import com.example.navigationexample.base.AppPlatform.navTo
import com.example.navigationexample.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>({
    FragmentHomeBinding.inflate(it)
}) {

    override fun start() {
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnInit.setOnClickListener {
            navTo(R.id.go_to_destinationFragment)
        }
    }
}