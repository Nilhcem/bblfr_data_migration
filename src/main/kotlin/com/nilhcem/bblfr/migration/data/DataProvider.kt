package com.nilhcem.bblfr.migration.data

import com.nilhcem.bblfr.migration.model.InputData
import com.nilhcem.bblfr.migration.model.OutputData
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import okio.Buffer
import java.io.File

class DataProvider {

    companion object {
        val MOSHI = Moshi.Builder().build()
    }

    val inputData: InputData
    val sinceMap: Map<String, String>
    private val outputFile: File

    constructor(args: Array<String>) {
        if (args.size != 3) throw IllegalArgumentException("Invalid args number.%nHave you run the app through the run.sh file?")

        val inputJson = File(args[0]).readText().replaceFirst(Regex("[^{]*"), "")
        inputData = MOSHI.adapter(InputData::class.java).fromJson(inputJson)

        sinceMap = File(args[1]).readLines().map {
            val spaceIdx = it.indexOf(" ")
            it.substring(spaceIdx + 1) to it.substring(0, spaceIdx)
        }.toMap()

        outputFile = File(args[2])
    }

    fun persistOutputData(output: OutputData) {
        println("Persist output data")

        outputFile.printWriter().use { out ->
            out.print("var data = ")

            val buffer = Buffer()
            val jsonWriter = JsonWriter.of(buffer)
            jsonWriter.setIndent("\t")
            MOSHI.adapter(OutputData::class.java).toJson(jsonWriter, output)
            out.println(buffer.readUtf8())
        }
    }
}
