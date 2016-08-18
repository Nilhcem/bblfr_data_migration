package com.nilhcem.bblfr.migration.model

data class InputData(val baggers: List<InputBagger>, val cities: List<InputCity>)

data class InputBagger(val name: String, val bio: String, val picture: String, val websites: List<InputWebsite>, val twitter: String, val contact: String, val mail: String, val location: String, val sessions: List<InputSession>, val tags: List<String>, val cities: List<String>)
data class InputWebsite(val title: String, val href: String)
data class InputSession(val title: String, val summary: String)

data class InputCity(val name: String, val ville_img: String, val lat: Float, val lng: Float)
