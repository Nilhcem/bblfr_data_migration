package com.nilhcem.bblfr.migration

import com.nilhcem.bblfr.migration.data.DataProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val LOG: Logger = LoggerFactory.getLogger("App")

fun main(args: Array<String>) {
    LOG.info("BBLFR data migration tool")
    DataProvider()
}
