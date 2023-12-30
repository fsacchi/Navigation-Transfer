package com.example.navigationexample.fragments

import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.base.AppPlatform.backPasswordValidation
import com.example.navigationexample.databinding.FragmentSuccessBinding
import com.example.navigationexample.model.PasswordValidationState

class SuccessFragment : BaseFragment<FragmentSuccessBinding>({
    FragmentSuccessBinding.inflate(it)
}) {

    override fun start() {
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnHome.setOnClickListener{
            backPasswordValidation(PasswordValidationState.SUCCESS)
        }
    }


}