package com.yunsung.coroutine

import android.content.ClipData
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import com.yunsung.coroutine.databinding.ActivityMainBinding
import com.yunsung.coroutine.databinding.NaverSearchItemBinding

class NaverListAdapter(
    private val binding: NaverSearchItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(item : Item) = with(binding){
         naverSearchHeaderTextView.text = item.title
         naverSearchIntentTextView.text = item.description

        naverContent.setOnClickListener { showDialog(root,item) }
    }

    private fun showDialog(root: View, item: Item) {

    }

}