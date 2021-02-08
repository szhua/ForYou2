package com.szhua.foryou.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.szhua.foryou.data.BmobDiaryAndFav
import com.szhua.foryou.databinding.FavItemsLayoutBinding

class FavDiariesAdapter :ListAdapter<BmobDiaryAndFav,FavDiariesAdapter.FavDiariesViewHolder>(FavDiariesDiffCallback()) {


    var itemLongClickListener: ((BmobDiaryAndFav) -> Unit) ?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavDiariesViewHolder {
        return  FavDiariesViewHolder(
                FavItemsLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: FavDiariesViewHolder, position: Int) {
            getItem(position)?.let { diary->
                 holder.bind(diary)
                 holder.itemView.setOnLongClickListener {
                     itemLongClickListener?.let { call -> call(diary) }
                     false
                 }
            }
    }

    class  FavDiariesViewHolder(val binding:FavItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
            fun  bind(item : BmobDiaryAndFav){
                 binding.apply {
                     diary = item
                     executePendingBindings()

                 }
            }

    }




}
class   FavDiariesDiffCallback : DiffUtil.ItemCallback<BmobDiaryAndFav>(){
    override fun areItemsTheSame(oldItem: BmobDiaryAndFav, newItem: BmobDiaryAndFav): Boolean {
         return  oldItem.bMobDiary.objectId == newItem.bMobDiary.objectId
    }

    override fun areContentsTheSame(oldItem: BmobDiaryAndFav, newItem: BmobDiaryAndFav): Boolean {
         return  oldItem == newItem
    }
}


