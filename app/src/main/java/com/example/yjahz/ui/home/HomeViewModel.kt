package com.example.yjahz.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yjahz.model.category.Category
import com.example.yjahz.model.seller.Seller
import com.example.yjahz.remote_source.SellersRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel constructor(private var remoteSource: SellersRemoteSource) : ViewModel() {


    private val _popularSellers = MutableLiveData<ArrayList<Seller>>()
    val popularSellers: LiveData<ArrayList<Seller>> = _popularSellers

    private val _trendingSellers = MutableLiveData<ArrayList<Seller>>()
    val trendingSellers: LiveData<ArrayList<Seller>> = _trendingSellers

    private val _categories = MutableLiveData<ArrayList<Category>>()
    val categories: LiveData<ArrayList<Category>> = _categories


    fun loadDataFromApi() {

        viewModelScope.launch(Dispatchers.Main) {
            try {
                _categories.postValue(remoteSource.getCategories())
                _popularSellers.postValue(remoteSource.getPopularSellers())
                _trendingSellers.postValue(remoteSource.getTrendingSellers())
            } catch (ex: Exception) {
                Log.e("TAG", "loadDataFromApi: $ex")
            }

        }

    }

}