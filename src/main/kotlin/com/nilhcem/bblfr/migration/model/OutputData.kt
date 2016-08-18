package com.nilhcem.bblfr.migration.model

data class OutputData(val speakers: List<Speaker>, val cities: Map<String, City>)

data class Speaker(val since: String, val name: String, val bio: String, val picture: String, val websites: List<Website>, val location: String, val sessions: List<Session>, val cities: List<String>, val contacts: Contacts)
data class Website(val name: String, val url: String)
data class Session(val title: String, val abstract: String, val tags: List<String>, val lang: List<String>)
data class Contacts(val twitter: String?, val mail: String)

data class City(val name: String, val ville_img: String, val lat: Float, val lng: Float)
