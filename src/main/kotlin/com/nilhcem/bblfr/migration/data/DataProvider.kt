package com.nilhcem.bblfr.migration.data

import com.nilhcem.bblfr.migration.model.InputData
import com.nilhcem.bblfr.migration.model.OutputData
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import okio.Buffer
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevWalk
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.TreeWalk
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class DataProvider {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(DataProvider::class.java)
        val MOSHI = Moshi.Builder().build()

        val GIT_URL = "https://github.com/brownbaglunch/bblfr_data.git"
        val GIT_BRANCH = "gh-pages"
        val RH_FILE = "baggers-rh.js"
    }

    val inputDir: File
    val outputDir: File
    val repository: Repository

    constructor() {
        inputDir = createDir("input")
        outputDir = createDir("output")
        cloneRepo(GIT_URL, GIT_BRANCH, inputDir)
        repository = getRepo(File(inputDir, ".git"))
    }

    fun getInputData(): InputData {
        val lastCommitId = repository.resolve(Constants.HEAD)
        val revWalk = RevWalk(repository)
        try {
            val treeWalk = TreeWalk.forPath(repository, RH_FILE, revWalk.parseCommit(lastCommitId).tree)
            val fileContent = repository.open(treeWalk.getObjectId(0)).bytes.toString(Charsets.UTF_8)

            // Response starts with "var data = {", which we should remove.
            val json = fileContent.replaceFirst(Regex("[^{]*"), "")

            return MOSHI.adapter(InputData::class.java).fromJson(json)
        } finally {
            revWalk.dispose()
        }
    }

    fun persistOutputData(output: OutputData) {
        LOG.info("Persist output data")

        File(outputDir, RH_FILE).printWriter().use { out ->
            out.print("var data = ")

            val buffer = Buffer()
            val jsonWriter = JsonWriter.of(buffer)
            jsonWriter.setIndent("\t")
            MOSHI.adapter(OutputData::class.java).toJson(jsonWriter, output)
            out.println(buffer.readUtf8())
        }
    }

    private fun createDir(name: String): File {
        LOG.info("Create directory: $name")

        val dir = File(name)
        if (dir.exists()) {
            dir.deleteRecursively()
        }
        dir.mkdirs()
        return dir
    }

    private fun cloneRepo(url: String, branch: String, directory: File) {
        LOG.info("Clone repository: $url")

        Git.cloneRepository()
                .setURI(url)
                .setDirectory(directory)
                .setBranch(branch)
                .call()

        LOG.info("Cloned at: ${directory.absolutePath}")
    }

    private fun getRepo(gitDir: File) = FileRepositoryBuilder().setMustExist(true).setGitDir(gitDir).build()
}
