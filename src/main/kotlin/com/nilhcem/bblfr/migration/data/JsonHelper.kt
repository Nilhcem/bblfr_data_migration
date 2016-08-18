package com.nilhcem.bblfr.migration.data

import com.nilhcem.bblfr.migration.model.InputData
import com.nilhcem.bblfr.migration.model.OutputData
import com.squareup.moshi.Moshi

object JsonHelper {

    val MOSHI = Moshi.Builder().build()
    val INPUT_ADAPTER = MOSHI.adapter(InputData::class.java)
    val OUTPUT_ADAPTER = MOSHI.adapter(OutputData::class.java)
}
