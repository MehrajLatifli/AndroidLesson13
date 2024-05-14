package com.example.androidlesson13.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidlesson13.api.ApiUtils
import com.example.androidlesson13.models.LoginRequest
import com.example.androidlesson13.models.LoginResponse
import com.example.androidlesson13.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private val api = ApiUtils.createApi()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val request = LoginRequest(password, username)
                login(request)

            } else {
                Toast.makeText(context, "It cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(loginReques: LoginRequest) {

       // username: "mor_2314",
        // password: "83r5^_"

        binding.progressBar.visibility = View.VISIBLE
        api.loginUserApi(loginReques).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }
                } else {
                    Toast.makeText(context, "Wrong information", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE

                binding.progressBar.visibility = View.GONE
                Log.e("API_CALL", "Login API call failed", t)
            }
        })
    }
}