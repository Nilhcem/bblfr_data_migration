package com.nilhcem.bblfr.migration

import com.nilhcem.bblfr.migration.data.DataProvider
import com.nilhcem.bblfr.migration.model.Mapper

fun main(args: Array<String>) {
    val dataProvider = DataProvider(args)
    dataProvider.persistOutputData(Mapper.toOutputData(dataProvider.inputData, dataProvider.sinceMap))
}
