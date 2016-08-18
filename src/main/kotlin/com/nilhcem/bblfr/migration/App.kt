package com.nilhcem.bblfr.migration

import com.nilhcem.bblfr.migration.data.DataProvider
import com.nilhcem.bblfr.migration.model.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val LOG: Logger = LoggerFactory.getLogger("App")

fun main(args: Array<String>) {
    LOG.info("BBLFR data migration tool")

    val dataProvider = DataProvider()
    val outputData = Mapper.toOutputData(dataProvider.getInputData())
    dataProvider.persistOutputData(outputData)

    LOG.info("Migration terminated")
}
