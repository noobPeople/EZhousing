package com.okky.ezhousing.ui.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.okky.ezhousing.MainActivity
import com.okky.ezhousing.R
import com.okky.ezhousing.api.ApiConfig
import com.okky.ezhousing.api.response.LoginUserResponse
import com.okky.ezhousing.databinding.ActivityLoginBinding
import com.okky.ezhousing.model.UserModel
import com.okky.ezhousing.preference.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding

    private lateinit var userPreference: UserPreference
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        userPreference = UserPreference(this)

        binding?.btnRegister?.setOnClickListener {
            val email = binding?.emailEdittext?.text.toString()
            val password = binding?.passwordEdittext?.text.toString()

            postLogin(email, password)
        }

//        binding?.t?.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
//        }
    }

    override fun onStart() {
        super.onStart()
        userModel = userPreference.getUser()
        if (userModel.isLogin) {
            moveMainActivity()
            finish()
        }
    }

    private fun postLogin(email: String, password: String) {
        isLoading(true)
        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object : Callback<LoginUserResponse> {
            override fun onResponse(
                call: Call<LoginUserResponse>,
                response: Response<LoginUserResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val name = responseBody.loginResult.name
                        val token = responseBody.loginResult.token

                        saveUser(name, email, token)
                        moveMainActivity()
                        isLoading(false)
                    }
                } else {
                    isLoading(false)
                    Toast.makeText(this@LoginActivity, "Email atau password tidak tepat", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                isLoading(false)
                Toast.makeText(this@LoginActivity, "Koneksi error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun saveUser(name: String, email: String, token: String) {
        userModel.name = name
        userModel.email = email
        userModel.token = token
        userModel.isLogin = true

        userPreference.setUser(userModel)
    }

    private fun moveMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun isLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) {
            View.VISIBLE
        } else View.GONE
    }
}