package com.example.matrixapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.matrixapp.model.City
import com.example.matrixapp.model.Location

class LocationViewModel(app: Application) : AndroidViewModel(app) {

    val privateLocations = MutableLiveData<List<Location>>()
    val freeLocations = MutableLiveData<List<Location>>()

    private val privateLocationsList = listOf(
        Location(
            "Japan",
            listOf(
                City("Tokyo", false),
            ),
            "",
            false
        ),
        Location(
            "USA",
            listOf(
                City("Detroit", false),
                City("New York", false),
                City("Chicago", false),
                City("Los Angeles", false),
            ),
            "",
            false
        ),
        Location(
            "Russia",
            listOf(
                City("Moscow", false),
            ),
            "",
            false
        ),
        Location(
            "Germany",
            listOf(
                City("Berlin", false),
                City("Leipzig", false),
            ),
            "",
            false
        )
    )

    private val freeLocationsList = listOf(
        Location(
            "Turkey",
            listOf(
                City("Ankara", true),
            ),
            "",
            false
        ),

        Location(
            "USA",
            listOf(
                City("Detroit", false),
                City("New York", false),

                ),
            "",
            false
        ),
        Location(
            "Russia",
            listOf(
                City("Kazan", false),
            ),
            "",
            false
        ),
        Location(
            "Italy",
            listOf(
                City("Rome", false),
                City("Piza", false),
            ),
            "",
            false
        )
    )

    fun getLocations() {
        privateLocations.value = privateLocationsList
        freeLocations.value = freeLocationsList
    }

    fun selectLocation(city: City): City {
        freeLocations.value?.forEach { it ->
            it.isExposed = false
            it.cities.forEach {
                if (it != city) {
                    it.isSelected = false
                }
            }
        }
        privateLocations.value?.forEach { it ->
            it.cities.forEach {
                if (it != city) {
                    it.isSelected = false
                }
            }
        }
        if(!city.isSelected){
            city.isSelected = true
        }
        Log.d("list", freeLocationsList.toString())
        return city
    }
}