package com.example.navigationexample.fragments

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.R
import com.example.navigationexample.base.AppPlatform.enable
import com.example.navigationexample.base.AppPlatform.navTo
import com.example.navigationexample.base.AppPlatform.observe
import com.example.navigationexample.base.AppPlatform.put
import com.example.navigationexample.base.AppPlatform.value
import com.example.navigationexample.databinding.FragmentHomeBinding
import com.example.navigationexample.databinding.FragmentNameDestinationBinding
import com.example.navigationexample.model.TransferModel

class DestinationFragment : BaseFragment<FragmentNameDestinationBinding>({
    FragmentNameDestinationBinding.inflate(it)
}) {

    override fun start() {
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnInit.setOnClickListener {
            val transferModel = TransferModel(namePeople = binding.editTextName.value())
            navTo(R.id.go_to_value_fragment, Bundle().put("transferModel", transferModel))
        }

        binding.editTextName.observe {
            binding.btnInit.enable(it.isNotBlank())
        }
    }


}