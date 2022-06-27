package com.yunsung.coroutine.util.extension

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.yunsung.coroutine.data.naverdata.remote.naver.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.showToast(message : String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}

fun AlertDialog.Builder.showNewsDialog(root: View, item: Item) {

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
