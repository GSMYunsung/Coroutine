package com.yunsung.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import com.yunsung.coroutine.base.BaseActivity
import com.yunsung.coroutine.databinding.ActivityMainBinding
import com.yunsung.coroutine.util.NetWorkResult
import com.yunsung.coroutine.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    private val schoolViewModel: ArtGalleryViewModel by viewModels()
    private val naverSearchViewModel : NaverSearchViewModel by viewModels()

    override fun init() {

        //schoolViewModel.getArtGalleryList()
        naverSearchViewModel.getNaverSearchData()

        naverSearchViewModel.naverSearchDataList.observe(this){
                response ->

            when(response){
                is NetWorkResult.Success -> {
                    Log.d("TAG", "schoolList: ${response.data}")
                    showToast("데이터 호출 성공!")
                }
                is NetWorkResult.Error -> {
                    Log.d("Error", "schoolList: ${response.message}")
                }
                is NetWorkResult.Loading -> {
                    Log.d("TAG", "schoolList: Loading")
                }
            }
        }

        with(binding){
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(p0: String?): Boolean {

                        return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    Log.d("cocopam",p0.toString())
                        return false
                }

            })
        }



        schoolViewModel.artGalleryList.observe(this){
            response ->

            when(response){
                is NetWorkResult.Success -> {
                    Log.d("TAG", "schoolList: ${response.data}")
                    showToast("데이터 호출 성공!")
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