package com.inspirecoding.supershopper.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inspirecoding.supershopper.model.ListItem
import com.inspirecoding.supershopper.model.ShoppingList

class ShoppingListFragmentViewModel: ViewModel()
{
    var openedShoppingList: ShoppingList = ShoppingList()




    private val _openItemsMLD = MutableLiveData<MutableList<ListItem>>()
    val openItemsLD: LiveData<MutableList<ListItem>>
        get() = _openItemsMLD
    private val _boughtItemsMLD = MutableLiveData<MutableList<ListItem>>()
    val boughtItemsLD: LiveData<MutableList<ListItem>>
        get() = _boughtItemsMLD


    fun updateOpenItems(listOfItems: MutableList<ListItem>)
    {
        _openItemsMLD.value = listOfItems
    }
    fun updateBoughtItems(listOfItems: MutableList<ListItem>)
    {
        _boughtItemsMLD.value = listOfItems
    }
}