package com.wdretzer.api_weathermap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wdretzer.api_weathermap.model.Sys
import com.wdretzer.api_weathermap.model.TemperatureData
import com.wdretzer.api_weathermap.model.Weather
import com.wdretzer.api_weathermap.model.Wind
import com.wdretzer.api_weathermap.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ApiViewModel(private val repository: ApiRepository = ApiRepository.instance) : ViewModel() {

    private val _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    private val _city = MutableLiveData<String>()
    val city: LiveData<String>
        get() = _city

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    private val _tempData = MutableLiveData<TemperatureData>()
    val tempData: LiveData<TemperatureData>
        get() = _tempData

    private val _windData = MutableLiveData<Wind>()
    val windData: LiveData<Wind>
        get() = _windData

    private val _sysData = MutableLiveData<Sys>()
    val sysData: LiveData<Sys>
        get() = _sysData


    fun getData(city: String) = viewModelScope.launch(Dispatchers.Main){
        repository
            .getData(city)
            .catch { _error.value = true }
            .collect {
                _city.postValue(it.name)
                _weather.postValue(it.weather.first())
                _tempData.postValue(it.main)
                _windData.postValue(it.wind)
                _sysData.postValue(it.sys)
            }
    }
}
