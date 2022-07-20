package com.example.tbctaskk11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tbctaskk11.databinding.FirstLayoutBinding
import com.example.tbctaskk11.databinding.SecondLayoutBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onDeleteClickListener: ((Attributes) -> Unit)? = null
    var onEditClickListener: ((Attributes,Int) -> Unit)? = null

    private var attributesList = listOf<Attributes>()


    inner class FirstViewHolder(var binding: FirstLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pos:Int) {
            val attributes = attributesList[adapterPosition]
            binding.apply {
                TextView1.text = attributes.description
                imageView.setImageResource(attributes.image!!)
                //pp
                editBtn1.setOnClickListener {
                    onEditClickListener?.invoke(attributes,pos)
                }
//pp
                deleteBtn1.setOnClickListener {
                    onDeleteClickListener?.invoke(attributes)
                }
            }
        }
    }

    inner class SecondViewHolder(var binding: SecondLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pos:Int) {
            val attributes = attributesList[adapterPosition]
            binding.apply {
                textView.text = attributes.description
//pp
                editBtn2.setOnClickListener {
                    onEditClickListener?.invoke(attributes,pos)
                }
//pp
                deleteBtn2.setOnClickListener {
                    onDeleteClickListener?.invoke(attributes)
                }
            }
        }
    }

    fun setData(newList: List<Attributes>) {
        val callback = AttributesDiffCallBack(attributesList, newList)
        val result = DiffUtil.calculateDiff(callback)
        attributesList = newList
        result.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.FIRST_VIEW_HOLDER -> FirstViewHolder(
                FirstLayoutBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> SecondViewHolder(
                SecondLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FirstViewHolder -> holder.bind(position)
            is SecondViewHolder -> holder.bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val attributes = attributesList[position]
        return when (attributes.image != null) {
            true -> ViewTypes.FIRST_VIEW_HOLDER
            else -> ViewTypes.SECOND_VIEW_HOLDER
        }

    }

    override fun getItemCount(): Int = attributesList.size


    inner class AttributesDiffCallBack(
        private val oldList: List<Attributes>,
        private val newList: List<Attributes>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].description == newList[newItemPosition].description
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}