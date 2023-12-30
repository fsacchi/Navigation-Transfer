package com.example.navigationexample.fragments

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.R
import com.example.navigationexample.base.AppPlatform.backPasswordValidation
import com.example.navigationexample.base.AppPlatform.enable
import com.example.navigationexample.base.AppPlatform.navTo
import com.example.navigationexample.base.AppPlatform.observe
import com.example.navigationexample.base.AppPlatform.value
import com.example.navigationexample.databinding.FragmentPasswordBinding
import com.example.navigationexample.model.PasswordValidationState
import com.example.navigationexample.model.TransferModel

class PasswordFragment : BaseFragment<FragmentPasswordBinding>({
    FragmentPasswordBinding.inflate(it)
}) {

    private val args by navArgs<PasswordFragmentArgs>()
    private lateinit var transferModel: TransferModel


    override fun start() {
        transferModel = args.transferModel
        insertListeners()
    }

    override fun onResume() {
        super.onResume()
        binding.editTextPassword.text.clear()
    }

    private fun insertListeners() {
        binding.btnTransfer.setOnClickListener {
            transferModel.validated = (binding.editTextPassword.value() == PASSWORD_CORRECT)
            if (transferModel.validated) navTo(R.id.go_to_success) else navTo(R.id.go_to_error)
        }

        binding.editTextPassword.observe {
            binding.btnTransfer.enable(it.length == LENGTH_PASSWORD)
        }
    }

    companion object {
        const val PASSWORD_CORRECT = "123456"
        const val LENGTH_PASSWORD = 6
    }

}