package com.inspirecoding.supershopper.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.supershopper.R
import com.inspirecoding.supershopper.databinding.ItemOfShoppinglistOpenBoughtBinding
import com.inspirecoding.supershopper.enums.Prioirities
import com.inspirecoding.supershopper.enums.ShoppingListStatus
import com.inspirecoding.supershopper.model.ListItem
import com.inspirecoding.supershopper.utilities.toStringWithDecimal

private val TAG = "OpenBoughtItemsAdapter"
class OpenBoughtItemsAdapter(val context: Context): RecyclerView.Adapter<OpenBoughtItemsAdapter.OpenBoughItemsViewHolder>()
{
    private var listOfItems: MutableList<ListItem> = mutableListOf()
    private var shoppingListStatus: ShoppingListStatus = ShoppingListStatus.OPEN

    private var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener
    {
        fun onItemSelected(listItem: ListItem, isChecked: Boolean)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?)
    {
        this.onItemClickListener = onItemClickListener
    }


    fun addItem(listItem: ListItem)
    {
        listOfItems.add(listItem)
        notifyItemInserted(listOfItems.size)
    }
    fun updateItem(position: Int, listItem: ListItem)
    {
        listOfItems[position] = listItem
        notifyItemChanged(position)
    }
    fun removeItem(position: Int)
    {
        listOfItems.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listOfItems.size)
    }
    fun removeAllItems()
    {
        val size = listOfItems.size
        listOfItems.clear()
        notifyItemRangeRemoved(0, size)
    }
    fun addAllItem(listOfItems: MutableList<ListItem>)
    {
        this.listOfItems.clear()
        this.listOfItems.addAll(listOfItems)
        notifyDataSetChanged()
    }
    fun getItemFromPosition(position: Int): ListItem
    {
        return listOfItems[position]
    }

    fun getItemsList() = listOfItems

    fun getShoppingListStatus() = this.shoppingListStatus
    fun setShoppingListStatus(shoppingListStatus: ShoppingListStatus)
    {
        this.shoppingListStatus = shoppingListStatus
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenBoughItemsViewHolder
    {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemOfShoppinglistOpenBoughtBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_of_shoppinglist_open_bought,
            parent, false
        )

        return OpenBoughItemsViewHolder(binding)
    }
    override fun getItemCount() = listOfItems.size
    override fun onBindViewHolder(itemViewHolder: OpenBoughItemsViewHolder, position: Int)
    {
        itemViewHolder.bindItem(listOfItems[position])
    }

    inner class OpenBoughItemsViewHolder(val binding: ItemOfShoppinglistOpenBoughtBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener
    {
        init
        {
            binding.cbItemIsBought.setOnClickListener (this)
        }
        fun bindItem(listItem: ListItem)
        {
            binding.tvItem.text = listItem.item
            binding.tvQuantity.text = listItem.qunatity.toStringWithDecimal()
            binding.tvUnit.text = listItem.unit

            setPriority(listItem.priority)

            when(shoppingListStatus)
            {
                ShoppingListStatus.OPEN -> binding.cbItemIsBought.visibility = View.VISIBLE
                ShoppingListStatus.CLOSED -> binding.cbItemIsBought.visibility = View.INVISIBLE
                ShoppingListStatus.DONE -> binding.cbItemIsBought.visibility = View.VISIBLE
            }

            binding.cbItemIsBought.isChecked = listItem.isBought
        }
        override fun onClick(v: View?)
        {
            Log.d(TAG, "${binding.cbItemIsBought.isChecked}, $adapterPosition, ${View.generateViewId()}")
            onItemClickListener?.onItemSelected(listOfItems[adapterPosition], binding.cbItemIsBought.isChecked)
        }
        private fun setPriority(prioirities: Prioirities)
        {
            when (prioirities)
            {
                Prioirities.LOW -> {
                    binding.viewPriority.setBackgroundResource(R.drawable.shape_roundedleftside_green)
                    binding.viewPriority.visibility = View.VISIBLE
                }
                Prioirities.MEDIUM -> {
                    binding.viewPriority.setBackgroundResource(R.drawable.shape_roundedleftside_orange)
                    binding.viewPriority.visibility = View.VISIBLE
                }
                Prioirities.HIGH -> {
                    binding.viewPriority.setBackgroundResource(R.drawable.shape_roundedleftside_red)
                    binding.viewPriority.visibility = View.VISIBLE
                }
                Prioirities.EMPTY -> binding.viewPriority.visibility = View.INVISIBLE
            }
        }
    }






}