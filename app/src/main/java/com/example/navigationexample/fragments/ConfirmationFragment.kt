package com.example.navigationexample.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.gndi.beneficiario.gndieasy.core.platform.BaseFragment
import com.example.navigationexample.R
import com.example.navigationexample.base.AppPlatform.backingFromPasswordValidation
import com.example.navigationexample.base.AppPlatform.currencyFormat
import com.example.navigationexample.base.AppPlatform.navTo
import com.example.navigationexample.base.AppPlatform.navToPasswordValidation
import com.example.navigationexample.databinding.FragmentConfirmationBinding
import com.example.navigationexample.model.TransferModel

class ConfirmationFragment : BaseFragment<FragmentConfirmationBinding>({
    FragmentConfirmationBinding.inflate(it)
}) {
    private val args by navArgs<ValueFragmentArgs>()
    private lateinit var transferModel: TransferModel

    override fun start() {
        transferModel = args.transferModel
        binding.txtNamePeople.text = transferModel.namePeople
        binding.txtValue.text = transferModel.valueTransfer.currencyFormat(true)
        insertListeners()

        findNavController().backingFromPasswordValidation(
            success = {
                findNavController().popBackStack(R.id.homeFragment, false)
            },
            cancelled = {}
        )
    }

    private fun insertListeners() {
        binding.btnCancel.setOnClickListener {
            navTo(R.id.go_to_home)
        }

        binding.btnConfirm.setOnClickListener{
            navToPasswordValidation(transferModel)
        }
    }


}