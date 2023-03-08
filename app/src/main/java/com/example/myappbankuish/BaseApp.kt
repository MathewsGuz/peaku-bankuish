package com.example.myappbankuish

import android.app.Application
import com.example.myappbankuish.di.BankuishRetroComponent
import com.example.myappbankuish.di.DaggerBankuishRetroComponent
import com.example.myappbankuish.model.rest.BankuishRetroModule

class BaseApp : Application() {

    private lateinit var bankComponent: BankuishRetroComponent

    override fun onCreate() {
        super.onCreate()

        bankComponent = DaggerBankuishRetroComponent.builder().bankuishRetroModule(
            BankuishRetroModule()
        ).build()
    }

    fun getBankuishRetroComponent(): BankuishRetroComponent{
        return bankComponent
    }
}