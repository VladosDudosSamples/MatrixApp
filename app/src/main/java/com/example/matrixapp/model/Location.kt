package com.example.matrixapp.model

data class Location(
    val countryName: String,
    val cities: List<City>,
    val image: String,
    var isExposed: Boolean
)
