package com.coolgirl.madmeditation.screens.Main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.GetLocalUser
import com.coolgirl.madmeditation.Models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    private var user : UserLoginDataResponse? = SetUser()
    private var feelings : List<Feelings>? = null

    fun GetFeelings() : List<Feelings>?{
        return  feelings
    }
    fun GetUser() : UserLoginDataResponse?{
        return user
    }

    fun SetUser() : UserLoginDataResponse?{
        val user = GetLocalUser()
        return user
    }


    fun LoadFeelings(){
        var apiClient = ApiClient.start().create(ApiController::class.java)
        val call: Call<ResponseFeelings>? = apiClient.getFeelings()
        call!!.enqueue(object : Callback<ResponseFeelings?> {
            override fun onResponse(
                call: Call<ResponseFeelings?>,
                response: Response<ResponseFeelings?>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    feelings = responseData.data
                }
            }
            override fun onFailure(call: Call<ResponseFeelings?>, t: Throwable) {
            }
        })
    }
}