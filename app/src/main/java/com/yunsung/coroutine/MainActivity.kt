package com.yunsung.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.yunsung.coroutine.base.BaseActivity
import com.yunsung.coroutine.databinding.ActivityMainBinding
import com.yunsung.coroutine.util.NetWorkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    private val schoolViewModel: ArtGalleryViewModel by viewModels()

    override fun init() {

        schoolViewModel.getArtGalleryList()

        schoolViewModel.artGalleryList.observe(this){
            response ->

            when(response){
                is NetWorkResult.Success -> {
                    Log.d("TAG", "schoolList: ${response.data}")
                }
                is NetWorkResult.Error -> {
                    Log.d("Error", "schoolList: ${response.message}")
                }
                is NetWorkResult.Loading -> {
                    Log.d("TAG", "schoolList: Loading")
                }
            }
        }
    }

}