package com.nilhcem.bblfr.migration.model

import com.nilhcem.bblfr.migration.model.input.Bagger
import com.nilhcem.bblfr.migration.model.input.InputData
import com.nilhcem.bblfr.migration.model.output.Contacts
import com.nilhcem.bblfr.migration.model.output.OutputData
import com.nilhcem.bblfr.migration.model.output.Speaker
import com.nilhcem.bblfr.migration.model.input.City as InputCity
import com.nilhcem.bblfr.migration.model.input.Session as InputSession
import com.nilhcem.bblfr.migration.model.input.Website as InputWebsite
import com.nilhcem.bblfr.migration.model.output.City as OutputCity
import com.nilhcem.bblfr.migration.model.output.Session as OutputSession
import com.nilhcem.bblfr.migration.model.output.Website as OutputWebsite

object Mapper {

    fun toOutputData(inputData: InputData) = OutputData(toOutputSpeakers(inputData.baggers), toCitiesMap(inputData.cities))

    private fun toOutputSpeakers(input: List<Bagger>) = input.map {
        Speaker("TODO", it.name, it.bio, it.picture, toOutputWebsites(it.websites), it.location,
                toOutputSessions(it.sessions, it.tags), it.cities, Contacts(it.twitter, it.mail))
    }

    private fun toCitiesMap(input: List<InputCity>) = input.associateBy({ it.name }) { OutputCity(it.name, it.ville_img, it.lat, it.lng) }

    private fun toOutputWebsites(input: List<InputWebsite>) = input.map { OutputWebsite(it.title, it.href) }

    private fun toOutputSessions(input: List<InputSession>, tags: List<String>) = input.map {
        OutputSession(it.title, it.summary, tags, listOf("fr"))
    }
}
