package com.example.navigationexample

import com.example.navigationexample.base.BaseActivity
import com.example.navigationexample.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {

    override fun init() {}
}