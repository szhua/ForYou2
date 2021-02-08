package com.szhua.foryou.views

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.szhua.foryou.utils.toPx

class GridItemDecoration  : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if(parent.getChildLayoutPosition(view)%2==0){
            outRect.left =6.toPx()
            outRect.right =3.toPx()
        }else{
            outRect.left=3.toPx()
            outRect.right=6.toPx()
        }
    }


}