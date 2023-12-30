package com.example.navigationexample.fragments

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.R
import com.example.navigationexample.base.AppPlatform.bigDecimal
import com.example.navigationexample.base.AppPlatform.maskMoney
import com.example.navigationexample.base.AppPlatform.navTo
import com.example.navigationexample.base.AppPlatform.observe
import com.example.navigationexample.base.AppPlatform.put
import com.example.navigationexample.base.AppPlatform.value
import com.example.navigationexample.databinding.FragmentHomeBinding
import com.example.navigationexample.databinding.FragmentNameDestinationBinding
import com.example.navigationexample.databinding.FragmentValueBinding
import com.example.navigationexample.model.TransferModel

class ValueFragment : BaseFragment<FragmentValueBinding>({
    FragmentValueBinding.inflate(it)
}) {

    private val args by navArgs<ValueFragmentArgs>()
    private lateinit var transferModel: TransferModel

    override fun start() {
        transferModel = args.transferModel
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnNext.setOnClickListener {
            transferModel.valueTransfer = binding.editTextValue.value().bigDecimal().toDouble()
            navTo(R.id.go_to_confirmation_fragment, Bundle().put("transferModel", transferModel))
        }

        binding.editTextValue.maskMoney()
        binding.editTextValue.observe {
            val value = it.bigDecimal()
            binding.btnNext.isEnabled = value != "0".bigDecimal()
        }
    }


}