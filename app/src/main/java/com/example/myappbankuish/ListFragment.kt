package com.example.myappbankuish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappbankuish.model.BankuishViewModel
import com.example.myappbankuish.model.GitRepositories
import com.example.myappbankuish.model.ListGitRepositories
import com.example.myappbankuish.view.GitRepoAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var adapterRepo: GitRepoAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var gitList: ArrayList<GitRepositories>

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var gitList: List<GitRepositories>
    private lateinit var bankuishViewModel: BankuishViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //burnData()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        adapterRepo = GitRepoAdapter(this)
        recyclerView.adapter = adapterRepo
        initViewModel()

    }

    /**
    private fun burnData() {
        gitList = arrayListOf(
            GitRepositories("mateo", "fakeurl3"),
            GitRepositories("zeto", "fakeurl5"),
            GitRepositories("david", "fakeurl6"),
            GitRepositories("sebastian", "fakeurl9"),
            GitRepositories("andres", "fakeurl11"),
            GitRepositories("juan", "fakeurl21"),
            GitRepositories("mario", "fakeurl34")

        )
    }**/

    private fun initViewModel() {
        bankuishViewModel = ViewModelProvider(requireActivity()).get(BankuishViewModel::class.java)
        bankuishViewModel.getLiveDataResult().observe(this, object : Observer<ListGitRepositories> {
            override fun onChanged(t: ListGitRepositories?) {
                if (t != null) {
                    gitList= t.items!!
                    adapterRepo.setUpdatedData(t.items)
                    adapterRepo.notifyDataSetChanged()
                }

            }

        })
        bankuishViewModel.apiCall()
    }

    fun navigate(bundle: Bundle, repositories: GitRepositories) {
        findNavController().navigate(R.id.action_listFragment_to_userDetailsFragment,bundle)
    }
}