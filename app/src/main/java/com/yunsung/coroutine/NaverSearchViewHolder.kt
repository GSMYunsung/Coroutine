package com.yunsung.coroutine

import android.app.AlertDialog
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yunsung.coroutine.NaverSearchViewHolder.Companion.from
import com.yunsung.coroutine.base.BaseDiffUtil
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import com.yunsung.coroutine.databinding.ActivityMainBinding
import com.yunsung.coroutine.databinding.NaverSearchItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NaverSearchViewHolder(
    private val binding: NaverSearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) = with(binding) {
        naverSearchHeaderTextView.text = item.title
        naverSearchIntentTextView.text = item.description

        naverContent.setOnClickListener { showDialog(root, item) }
    }

    companion object {
        fun from(parent: ViewGroup): NaverSearchViewHolder = NaverSearchViewHolder(
            NaverSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private fun showDialog(root: View, item: Item) {
        AlertDialog.Builder(root.context)
            .setTitle("기사 확인")
            .setMessage("${item.title} :  다음 기사를 보시겠습니까?")
            .setPositiveButton("확인") { _, _ ->

                // 디스크 또는 네트워크 I/O 작업을 실행하는데 최적화 되어있는 디스패쳐
                CoroutineScope(Dispatchers.IO).launch {
                    root.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(item.link)
                        )
                    )
                }
            }
            .create()
            .show()
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