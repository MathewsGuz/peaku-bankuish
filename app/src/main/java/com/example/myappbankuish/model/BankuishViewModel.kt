package com.example.myappbankuish.model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myappbankuish.BaseApp
import com.example.myappbankuish.R
import com.example.myappbankuish.model.rest.BankuishService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class BankuishViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var bService: BankuishService

    private lateinit var liveDataResult:MutableLiveData<ListGitRepositories>
    init {
        //app injection
        (application as BaseApp).getBankuishRetroComponent().inject(this)
        liveDataResult = MutableLiveData()
    }

    fun getLiveDataResult():MutableLiveData<ListGitRepositories>{
        return liveDataResult
    }

    fun apiCall(){
        bService.getRepositories("kotlin",20,1)?.enqueue(
            object : Callback<ListGitRepositories> {
                override fun onResponse(
                    call: Call<ListGitRepositories>,
                    response: Response<ListGitRepositories>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(getApplication(), R.string.success_call,Toast.LENGTH_SHORT).show()
                        liveDataResult.postValue(response.body())
                    }else{
                        Toast.makeText(getApplication(), R.string.request_error,Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ListGitRepositories>, t: Throwable) {
                    Toast.makeText(getApplication(), R.string.network_error,Toast.LENGTH_SHORT).show()
                }

            }
        )
    }
}