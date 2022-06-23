package com.yunsung.coroutine.ui.news

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yunsung.coroutine.ui.news.NaverSearchViewHolder.Companion.from
import com.yunsung.coroutine.base.BaseDiffUtil
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import com.yunsung.coroutine.databinding.NaverSearchItemBinding
import com.yunsung.coroutine.util.extension.showNewsDialog

class NaverSearchViewHolder(
    private val binding: NaverSearchItemBinding

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) = with(binding) {

        Log.d("cocopam",item.title)

        naverSearchHeaderTextView.text = item.title
        naverSearchIntentTextView.text = item.description

        val altDialog = AlertDialog.Builder(root.context)

        naverContent.setOnClickListener { altDialog.showNewsDialog(root, item) }

    }

    companion object {
        fun from(parent: ViewGroup): NaverSearchViewHolder = NaverSearchViewHolder(
            NaverSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}

    class NaverSearchAdapter : RecyclerView.Adapter<NaverSearchViewHolder>(){

        var naverSearchList : List<Item> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverSearchViewHolder = from(parent)

        override fun onBindViewHolder(holder: NaverSearchViewHolder, position: Int) {
           holder.bind(naverSearchList[position])
        }

        override fun getItemCount(): Int = naverSearchList.size

        fun setData(newList: List<Item>) {

            val schoolListDiffUtil = BaseDiffUtil(naverSearchList , newList)
            val diffUtilResult = DiffUtil.calculateDiff(schoolListDiffUtil)

            naverSearchList = newList

            diffUtilResult.dispatchUpdatesTo(this)
        }


    }