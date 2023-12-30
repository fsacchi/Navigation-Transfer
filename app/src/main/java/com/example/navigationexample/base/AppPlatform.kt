package com.example.navigationexample.base

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.navigationexample.R
import com.example.navigationexample.model.PasswordValidationState
import com.example.navigationexample.model.TransferModel
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

object AppPlatform {

    fun Fragment.navTo(directions: Int) {
        findNavController().navigate(directions)
    }

    fun Fragment.navTo(directions: Int, bundle: Bundle) {
        findNavController().navigate(directions, bundle)
    }

    fun Fragment.navToPasswordValidation(
        transferModel: TransferModel
    ) {
        findNavController().navigate(
            R.id.go_to_nav_password,
            Bundle().put("transferModel", transferModel)
        )
    }

    fun Fragment.backPasswordValidation(state: PasswordValidationState) {
        findNavController().apply {
            previousBackStackEntry?.savedStateHandle?.apply {
                set(RESULT_KEY, state)
            }
            popBackStack()
        }
    }

    fun NavController.backingFromPasswordValidation(
        success: () -> Unit,
        cancelled: () -> Unit = {}
    ) {
        val savedState = currentBackStackEntry?.savedStateHandle

        when (savedState?.remove<PasswordValidationState>(RESULT_KEY)) {
            PasswordValidationState.SUCCESS -> success()
            PasswordValidationState.CANCELLED -> cancelled()
            else -> Unit
        }
    }

    fun EditText.observe(listener: (value: String) -> Unit) {
        let { edit ->
            val watcher = object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                    listener(text.toString())
                }
            }
            edit.addTextChangedListener(watcher)
        }
    }

    fun EditText.value(): String = text.toString()

    fun EditText.maskMoney(useSymbol: Boolean = false) {
        let { edit ->
            val watcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(
                    text: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }

                override fun afterTextChanged(editable: Editable) {
                    val text = editable.toString()
                    edit.isSelected = text.isNotEmpty() && "0".bigDecimal() != text.bigDecimal()

                    if (text.isEmpty()) return

                    edit.removeTextChangedListener(this)

                    val decimal = text.bigDecimal().toDouble()

                    val formatted = decimal.currencyFormat(useSymbol)

                    edit.setText(formatted)
                    edit.setSelection(edit.length())

                    edit.addTextChangedListener(this)
                }
            }
            edit.addTextChangedListener(watcher)
        }
    }

    fun View.enable(enabled: Boolean) {
        isEnabled = enabled
    }

    fun String.bigDecimal(): BigDecimal {
        return try {
            val clean = replace("\\D".toRegex(), "")
            BigDecimal(clean).movePointLeft(2)
        } catch (e: Exception) {
            0.toBigDecimal()
        }
    }

    fun Double.currencyFormat(useSymbol: Boolean = true): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
        val symbols = numberFormat.decimalFormatSymbols?.apply {
            currencySymbol = if (useSymbol) "$currencySymbol " else ""
        }

        numberFormat.decimalFormatSymbols = symbols
        return numberFormat.format(this).trim()
    }

    fun <T> Bundle.put(key: String, value: T?) = apply {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Parcelable -> putParcelable(key, value)
            else -> throw RuntimeException("not implemented")
        }
    }

    const val RESULT_KEY = "resultKey"
}