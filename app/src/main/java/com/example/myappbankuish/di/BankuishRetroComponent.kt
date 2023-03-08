package com.example.myappbankuish.di

import com.example.myappbankuish.model.BankuishViewModel
import com.example.myappbankuish.model.rest.BankuishRetroModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BankuishRetroModule::class])
interface BankuishRetroComponent {

    fun inject(mainActivityViewModule: BankuishViewModel)
}