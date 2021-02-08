package com.szhua.foryou.pages

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.szhua.foryou.adapter.DiaryAdapter
import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.data.BMobDiary
import com.szhua.foryou.databinding.ActivityMainBinding
import com.szhua.foryou.databinding.PageDiariesLayoutBinding
import com.szhua.foryou.utils.toPx
import com.szhua.foryou.viewmodles.DiariesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

/**
 * 首页。
 */
@AndroidEntryPoint
class DiariesPage : Fragment(){

    private  var job : Job?=null
    private  val viewModel : DiariesViewModel by viewModels()
    private lateinit var binding : PageDiariesLayoutBinding
    private val  diaryAdapter : DiaryAdapter by lazy { DiaryAdapter() }


    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PageDiariesLayoutBinding.inflate(inflater,container,false)
                .apply {
                    recyclerView.adapter = diaryAdapter
                    recyclerView.addItemDecoration(object :RecyclerView.ItemDecoration(){
                        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                             val position = parent.getChildLayoutPosition(view)
                             outRect.right =6.toPx()
                             outRect.left =6.toPx()
                             outRect.top =6.toPx()
                             if (position==parent.childCount-1){
                                 outRect.bottom =12.toPx()
                             }
                        }
                    })
                    swipeRefreshLayout.setOnRefreshListener {
                        diaryAdapter.refresh()
                    }
                    favs.setOnClickListener {
                        findNavController()
                                .navigate(DiariesPageDirections.actionDiariesPageToFavDiaryPage())
                    }
                }
        subscribeUI()
        return binding.root
    }

    @ExperimentalPagingApi
    private fun subscribeUI() {
        binding.swipeRefreshLayout.setColorSchemeColors(
            Color.BLUE, Color.GREEN, Color.CYAN, Color.RED
        )
        binding.lifecycleOwner =this
        diaryAdapter.addDataRefreshListener {
            binding.swipeRefreshLayout.isRefreshing=false
        }

        loadDiaries()
    }

    private fun loadDiaries() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.findDiaries().collectLatest { it ->
                val pagerData =  it.map {
                    viewModel.insertAllDiary(it)
                    it
                }
                diaryAdapter.submitData(pagerData)
               // viewModel.insertAllDiaries(diaries)
            }
        }
    }


}