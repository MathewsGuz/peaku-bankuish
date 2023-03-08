package com.example.myappbankuish

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.myappbankuish.databinding.FragmentUserDetailsBinding
import com.example.myappbankuish.model.GitRepositories

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailsFragment : Fragment() {

    private lateinit var result: GitRepositories
    private var userDetailsFragmentBinding: UserDetailsFragment? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            result = it.getParcelable<GitRepositories>("item")!!
            Log.d("nicolas", result.full_name.toString())
        }
        val binding = FragmentUserDetailsBinding.bind(view)
        binding.txtRepoName.text = "Name: " + result.name
        binding.txtCreated.text = "Creation Date: " + result.created_at
        binding.txtCloneUrl.text = "Clone at: " + result.clone_url
        binding.txtPushed.text = "Pushed date: " + result.pushed_at
        binding.txtUpdated.text = "Updated date: " + result.updated_at
        binding.txtLenguaje.text = "Language: " + result.language
        binding.txtDescription.text = "Descripction: " + result.description
        binding.txtUrl.text = "Repo Url: " + result.html_url
        binding.txtVisibility.text = "Public: " + result.visibility

        binding.txtUrl.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(result.html_url))
            startActivity(i)
        }
    }

}