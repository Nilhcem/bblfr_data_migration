package com.nilhcem.bblfr.migration.model

data class OutputData(val speakers: List<OutputSpeaker>, val cities: Map<String, OutputCity>)

data class OutputSpeaker(val since: String?, val name: String, val bio: String, val picture: String, val websites: List<OutputWebsite>, val location: String, val sessions: List<OutputSession>, val cities: List<String>, val contacts: OutputContacts)
data class OutputWebsite(val name: String, val url: String)
data class OutputSession(val title: String, val abstract: String, val tags: List<String>, val lang: List<String>)
data class OutputContacts(val twitter: String?, val mail: String)

data class OutputCity(val name: String, val ville_img: String, val lat: Float, val lng: Float)
