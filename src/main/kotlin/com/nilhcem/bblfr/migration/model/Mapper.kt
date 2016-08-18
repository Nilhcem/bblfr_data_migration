package com.nilhcem.bblfr.migration.model

object Mapper {

    fun toOutputData(inputData: InputData, sinceMap: Map<String, String>) =
            OutputData(toOutputSpeakers(inputData.baggers, sinceMap), toCitiesMap(inputData.cities))

    private fun toOutputSpeakers(input: List<InputBagger>, sinceMap: Map<String, String>) = input.map {
        OutputSpeaker(sinceMap[it.name], it.name, it.bio, it.picture,
                toOutputWebsites(it.websites), it.location, toOutputSessions(it.sessions, it.tags),
                it.cities, OutputContacts(it.twitter, it.mail))
    }

    private fun toCitiesMap(input: List<InputCity>) = input.associateBy({ it.name }) { OutputCity(it.name, it.ville_img, it.lat, it.lng) }

    private fun toOutputWebsites(input: List<InputWebsite>) = input.map {
        val title = if (it.href.contains("linkedin.com")) {
            "LinkedIn"
        } else if (it.href.contains("plus.google.com")) {
            "Google+"
        } else if (it.href.contains("viadeo.com")) {
            "Viadeo"
        } else {
            "Web"
        }

        OutputWebsite(title, it.href)
    }

    private fun toOutputSessions(input: List<InputSession>, tags: List<String>) = input.map {
        OutputSession(it.title, it.summary, tags, listOf("fr"))
    }
}
