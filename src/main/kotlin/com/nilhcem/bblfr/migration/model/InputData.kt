package com.nilhcem.bblfr.migration.model

data class InputData(val baggers: List<Bagger>, val cities: List<City>)

data class Bagger(val name: String, val bio: String, val picture: String, val websites: List<Website>, val twitter: String, val contact: String, val mail: String, val location: String, val sessions: List<Session>, val tags: List<String>, val cities: List<String>)
data class Website(val title: String, val href: String)
data class Session(val title: String, val summary: String)

data class City(val name: String, val ville_img: String, val lat: Float, val lng: Float)
