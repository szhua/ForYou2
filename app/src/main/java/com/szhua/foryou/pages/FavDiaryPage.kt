package com.szhua.foryou.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.szhua.foryou.R
import com.szhua.foryou.adapter.FavDiariesAdapter
import com.szhua.foryou.databinding.PageFavDiariesLayoutBinding
import com.szhua.foryou.viewmodles.FavDiariesViewModel
import com.szhua.foryou.views.GridItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavDiaryPage :Fragment() {


    private  val  adapter:FavDiariesAdapter by lazy {
        FavDiariesAdapter()
    }

    private val  viewMode:FavDiariesViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

      val binding =  DataBindingUtil.inflate<PageFavDiariesLayoutBinding>(
                inflater, R.layout.page_fav_diaries_layout,container,false
        ).apply {
          adapter.itemLongClickListener={diary->
              Snackbar.make(this.root,"删除？",Snackbar.LENGTH_LONG)
                      .setAction("确定") {
                          viewMode.deleteOne(diary.favoriteDiary)
                      }.show()
          }
          lifecycleOwner =viewLifecycleOwner
          (recyclerView.layoutManager as GridLayoutManager ).spanCount = 2
          recyclerView.adapter = adapter
          recyclerView.addItemDecoration(GridItemDecoration())
        }
        subscribeUI()
        return  binding.root
    }

    private fun subscribeUI() {
         viewMode.favDiaries.observe(viewLifecycleOwner){
             adapter.submitList(it)
         }
    }
}