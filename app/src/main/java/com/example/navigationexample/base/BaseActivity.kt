package com.example.navigationexample.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>() : AppCompatActivity() {

    constructor(bindingFactory: ((LayoutInflater) -> B)) : this() {
        this.bindingFactory = bindingFactory
    }

    private var bindingFactory: ((LayoutInflater) -> B)? = null
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (bindingFactory != null) {
            binding = bindingFactory!!.invoke(layoutInflater)
        }
        setContentView(binding.root)
        init()
    }


    abstract fun init()
}
