package com.example.myappbankuish.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myappbankuish.ListFragment
import com.example.myappbankuish.R
import com.example.myappbankuish.databinding.LayoutListItemBinding
import com.example.myappbankuish.model.GitRepositories

class GitRepoAdapter(private val fragment: ListFragment) : RecyclerView.Adapter<GitRepoAdapter.GitViewHolder>(){

    private var listData: List<GitRepositories>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val binding = LayoutListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GitViewHolder(binding,fragment)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null)return 0
        return listData?.size!!
    }

     class GitViewHolder(private val binding: LayoutListItemBinding, val fragment: ListFragment) : RecyclerView.ViewHolder(binding.root){
        fun bind(repositories: GitRepositories) {
            binding.apply{
                binding.txtAuthor.text = "Author: " + repositories.owner?.login
                binding.txtGitRepo.text = "Repository: " + repositories.name
                binding.gitImageView.load(repositories.owner?.avatar_url)
            }
            binding.root.setOnClickListener {
                //fragment.setFragmentResult("requestKey", bundleOf("bundleKey" to repositories))
                val bundle = Bundle()
                bundle.putParcelable("item",repositories)
                fragment.navigate(bundle,repositories)

                Log.d("YOUCLICKED",repositories.owner?.login.toString())
            }
        }
    }

    fun setUpdatedData(listData: List<GitRepositories>) {
        this.listData = listData
    }
}