package com.nilhcem.bblfr.migration.data

import org.eclipse.jgit.api.Git
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class DataProvider {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(DataProvider::class.java)
        val DIR_NAME = "input"
        val GIT_URL = "https://github.com/brownbaglunch/bblfr_data.git"
        val GIT_BRANCH = "gh-pages"
    }

    val inputDir: File

    constructor() {
        inputDir = createInputDir(DIR_NAME)
        cloneRepo(GIT_URL, GIT_BRANCH, inputDir)
    }

    private fun createInputDir(name: String): File {
        LOG.info("Create input directory")

        val inputDir = File(name)
        if (inputDir.exists()) {
            inputDir.deleteRecursively()
        }
        inputDir.mkdirs()
        return inputDir
    }

    private fun cloneRepo(url: String, branch: String, directory: File) {
        LOG.info("Clone repository: $url")

        Git.cloneRepository()
                .setURI(url)
                .setDirectory(directory)
                .setBranch(branch)
                .call()

        LOG.info("Cloned at ${directory.absolutePath}")
    }
}
