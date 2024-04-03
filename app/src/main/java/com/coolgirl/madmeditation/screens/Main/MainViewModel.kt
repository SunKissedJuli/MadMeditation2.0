package com.coolgirl.madmeditation.screens.Main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.Models.ApiClient
import com.coolgirl.madmeditation.Models.ApiController
import com.coolgirl.madmeditation.Models.Feelings
import com.coolgirl.madmeditation.Models.ResponseFeelings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var feelings : List<Feelings>? = null

    fun LoadFeelings() {
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