package com.nilhcem.bblfr.migration.model

object Mapper {

    fun toOutputData(inputData: InputData) = OutputData(toOutputSpeakers(inputData.baggers), toCitiesMap(inputData.cities))

    private fun toOutputSpeakers(input: List<InputBagger>) = input.map {
        OutputSpeaker("TODO", it.name, it.bio, it.picture, toOutputWebsites(it.websites), it.location,
                toOutputSessions(it.sessions, it.tags), it.cities, OutputContacts(it.twitter, it.mail))
    }

    private fun toCitiesMap(input: List<InputCity>) = input.associateBy({ it.name }) { OutputCity(it.name, it.ville_img, it.lat, it.lng) }

    private fun toOutputWebsites(input: List<InputWebsite>) = input.map { OutputWebsite(it.title, it.href) }

    private fun toOutputSessions(input: List<InputSession>, tags: List<String>) = input.map {
        OutputSession(it.title, it.summary, tags, listOf("fr"))
    }
}
