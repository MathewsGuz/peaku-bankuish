package com.example.myappbankuish.model.rest

import com.example.myappbankuish.model.GitRepositories
import com.example.myappbankuish.model.ListGitRepositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BankuishService {

    @GET("repositories")
    fun getRepositories(
        @Query("q") query: String,
        @Query("per_page") per: Int,
        @Query("page") page: Int
    ): Call<ListGitRepositories>

    @GET("repos/{owner}/{name}")
    fun getRepoDetails(
        @Path("owner") owner: String?,
        @Path("name") name: String?
    )
}