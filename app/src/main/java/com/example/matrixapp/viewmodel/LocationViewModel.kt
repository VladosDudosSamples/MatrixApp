package com.example.matrixapp.viewmodel

import android.app.Application
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
                City("Tokyo"),
            ),
            "",
            false
        ),
        Location(
            "USA",
            listOf(
                City("Detroit"),
                City("New York"),
                City("Chicago"),
                City("Los Angeles"),
            ),
            "",
            false
        ),
        Location(
            "Russia",
            listOf(
                City("Moscow"),
            ),
            "",
            false
        ),
        Location(
            "Germany",
            listOf(
                City("Berlin"),
                City("Leipzig"),
            ),
            "",
            false
        )
    )

    private val freeLocationsList = listOf(
        Location(
            "Turkey",
            listOf(
                City("Ankara"),
            ),
            "",
            true
        ),
        Location(
            "USA",
            listOf(
                City("Detroit"),
                City("New York"),

            ),
            "",
            false
        ),
        Location(
            "Russia",
            listOf(
                City("Kazan"),
            ),
            "",
            false
        ),
        Location(
            "Italy",
            listOf(
                City("Rome"),
                City("Piza"),
            ),
            "",
            false
        )
    )

    fun getLocations() {
        privateLocations.value = privateLocationsList
        freeLocations.value = freeLocationsList
    }

    fun selectLocation(location: Location){
        location.isSelected = true
    }
}