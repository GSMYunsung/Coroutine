package com.yunsung.coroutine.ui.news

import android.util.Log
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yunsung.coroutine.R
import com.yunsung.coroutine.base.BaseFragment
import com.yunsung.coroutine.databinding.FragmentNewsBinding
import com.yunsung.coroutine.util.NetWorkResult
import com.yunsung.coroutine.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(R.layout.fragment_news) {

    private val naverSearchViewModel : NaverSearchViewModel by viewModels()
    private val naverSearchAdapter by lazy { NaverSearchAdapter() }

    override fun init() {

        binding.naverSearchRecyclerView.adapter = naverSearchAdapter

//        naverSearchViewModel.naverSearchDataList.observe(this){
//                response ->
//
//            when(response){
//                is NetWorkResult.Success -> {
//                    Log.d("TAG", "schoolList: ${response.data}")
//
//                    naverSearchAdapter.setData(response.data!!)
//
//                    naverSearchViewModel.getNaverNews(naverSearchViewModel.searchQuery.value)
//
//                    requireActivity().showToast("데이터 호출 성공!")
//
//                }
//                is NetWorkResult.Error -> {
//                    Log.d("Error", "schoolList: ${response.message}")
//                }
//                is NetWorkResult.Loading -> {
//                    Log.d("TAG", "schoolList: Loading")
//                }
//            }
//        }

        // flow 는 emit 으로 구독을하고 collect 로 해당값의 변화를 확인한다.

        lifecycleScope.launch {
            naverSearchViewModel.searchQueryResult.collect { keyword ->
                Log.d("TAG", "init: $keyword")

                naverSearchViewModel.getNaverNews(keyword)

            }
        }

//        naverSearchViewModel.naverRoomData.observe(this){
//            local ->
//
//            naverSearchAdapter.setData(local)
//
//        }

        with(binding){
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(p0: String?): Boolean =  false

                override fun onQueryTextChange(liveText: String): Boolean {

                    naverSearchViewModel.searchQuery.value = liveText

                    return false
                }
            })
        }

    }
}