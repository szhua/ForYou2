package com.szhua.foryou.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.szhua.foryou.data.BMobDiary
import com.szhua.foryou.databinding.ListItemDiaryBinding
import com.szhua.foryou.pages.DiariesPageDirections

class DiaryAdapter : PagingDataAdapter<BMobDiary,DiaryAdapter.DiaryViewHolder>(DiaryDiffCallback()){



    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
          getItem(position)?.let {
              holder.bind(it)
          }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
           return  DiaryViewHolder(
                ListItemDiaryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
           )
    }


    class  DiaryViewHolder(private val binding : ListItemDiaryBinding) : RecyclerView.ViewHolder(binding.root){
            init {
                binding.setClickListener {
                  binding.diary?.let { diary->
                      val directions= DiariesPageDirections.actionDiariesPageToDiaryDetailPage(
                          diary.objectId
                      )
                      it.findNavController().navigate(directions)
                  }
                }
            }

            fun bind(item:BMobDiary){
                binding.apply {
                    diary = item
                    executePendingBindings()
                }
            }
    }

}



private class DiaryDiffCallback : DiffUtil.ItemCallback<BMobDiary>() {
    override fun areItemsTheSame(oldItem: BMobDiary, newItem: BMobDiary): Boolean {
        return oldItem.objectId == newItem.objectId
    }

    override fun areContentsTheSame(oldItem: BMobDiary, newItem: BMobDiary): Boolean {
        return oldItem == newItem
    }
}