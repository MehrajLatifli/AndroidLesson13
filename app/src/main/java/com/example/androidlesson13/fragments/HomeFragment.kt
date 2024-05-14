package com.example.androidlesson13.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlesson13.api.ApiUtils
import com.example.androidlesson13.adapters.UserAdapter
import com.example.androidlesson13.models.UserResponse
import com.example.androidlesson13.databinding.FragmentHomeBinding
import com.example.androidlesson13.models.UserResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val api = ApiUtils.createApi()

    private val userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUser.adapter=userAdapter

        getUser()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getUser() {
        api.getAllUserApi("2").enqueue(object : Callback<List<UserResponseItem>> {
            override fun onResponse(call: Call<List<UserResponseItem>>, response: Response<List<UserResponseItem>>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    userResponse?.let {
                        for (item in userResponse) {
                            Log.e("UserItem", item.toString())
                        }
                        userAdapter.updateList(userResponse)
                    }
                } else {
                    Log.e("response", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<UserResponseItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }


}