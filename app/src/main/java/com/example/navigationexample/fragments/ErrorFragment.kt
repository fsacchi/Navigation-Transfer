package com.example.navigationexample.fragments

import androidx.navigation.fragment.findNavController
import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.databinding.FragmentErrorBinding

class ErrorFragment : BaseFragment<FragmentErrorBinding>({
    FragmentErrorBinding.inflate(it)
}) {

    override fun start() {
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnRetry.setOnClickListener{
            findNavController().popBackStack()
        }
    }


}