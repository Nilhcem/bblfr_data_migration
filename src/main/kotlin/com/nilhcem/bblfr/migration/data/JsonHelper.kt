package com.nilhcem.bblfr.migration.data

import com.nilhcem.bblfr.migration.model.input.InputData
import com.squareup.moshi.Moshi

object JsonHelper {

    val MOSHI = Moshi.Builder().build()
    val INPUT_ADAPTER = MOSHI.adapter(InputData::class.java)
}
