package com.example.navigationexample

import com.example.navigationexample.base.BaseActivity
import com.example.navigationexample.databinding.ActivityMainBinding
import com.example.navigationexample.databinding.ActivityPasswordBinding

class PasswordActivity: BaseActivity<ActivityPasswordBinding>({
    ActivityPasswordBinding.inflate(it)
}) {

    override fun init() {}
}