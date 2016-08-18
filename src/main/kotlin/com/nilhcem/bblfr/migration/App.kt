package com.nilhcem.bblfr.migration

import org.eclipse.jgit.api.Git
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

val LOG: Logger = LoggerFactory.getLogger("App")

fun main(args: Array<String>) {
    LOG.info("BBLFR data migration tool")

    val inputDir = createInputDir("input")
    cloneRepo("https://github.com/brownbaglunch/bblfr_data.git", "gh-pages", inputDir)
}

fun createInputDir(name: String): File {
    LOG.info("Create input directory")

    val inputDir = File(name)
    if (inputDir.exists()) {
        inputDir.deleteRecursively()
    }
    inputDir.mkdirs()
    return inputDir
}

fun cloneRepo(url: String, branch: String, directory: File) {
    LOG.info("Clone repository: $url")

    Git.cloneRepository()
            .setURI(url)
            .setDirectory(directory)
            .setBranch(branch)
            .call()
}
