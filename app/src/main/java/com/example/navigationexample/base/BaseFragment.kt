package br.com.gndi.beneficiario.gndieasy.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.navigationexample.base.BaseActivity

abstract class BaseFragment<T: ViewBinding>(): Fragment() {

    protected lateinit var binding: T

    constructor(bindingFactory: ((LayoutInflater) -> T)) : this() {
        this.bindingFactory = bindingFactory
    }

    private var bindingFactory: ((LayoutInflater) -> T)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = bindingFactory!!(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    abstract fun start()
}
