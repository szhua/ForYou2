package com.szhua.foryou.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.orhanobut.logger.Logger
import com.szhua.foryou.R
import com.szhua.foryou.databinding.PageDiaryDetailPageBinding
import com.szhua.foryou.viewmodles.DiaryDetailViewModel
import com.szhua.foryou.viewmodles.DiaryDetailViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiaryDetailPage :Fragment() {


    private lateinit var  binding: PageDiaryDetailPageBinding
    private val args: DiaryDetailPageArgs by navArgs()

    @Inject
    lateinit var  diaryDetailViewModelFactory: DiaryDetailViewModelFactory

    private val   diaryDetailViewModel:DiaryDetailViewModel by viewModels {
            DiaryDetailViewModel.provideFactory(diaryDetailViewModelFactory ,args.objectId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          binding = DataBindingUtil
                .inflate<PageDiaryDetailPageBinding>(inflater,R.layout.page_diary_detail_page,container,false)
                .apply {
                    viewModel = diaryDetailViewModel
                    lifecycleOwner = viewLifecycleOwner
                    callback = CallBack {
                        diaryDetailViewModel.addFav()
                       //binding.fab.hide()
                       //findNavController().navigate(DiaryDetailPageDirections.actionDiaryDetailPageToFavDiaryPage())
                    }
                    toolbar.setNavigationOnClickListener {
                        findNavController().navigateUp()
                    }

                }
        subscribeUI()
        return  binding.root
    }

    private fun subscribeUI() {


    }

    fun interface  CallBack {
         fun addFav()
    }



}