package com.shlok.sampletestdownloader.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.vfs.VirtualFileManager
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

private val logger = Logger.getInstance("com.shlok.sampletestdownloader.utils.CodeforcesDownloader")

/**
 * Downloads sample tests from a Codeforces problem page.
 *
 * @param contestId the contest identifier (used in the file names).
 * @param problemId the problem identifier (used in the file names).
 * @param outputDirectory the directory path where the files should be saved.
 * @throws IOException if network errors occur or if the tests are not found.
 */
fun downloadSampleTests(contestId: String, problemId: String, outputDirectory: String) {
    val url = "https://codeforces.com/contest/$contestId/problem/$problemId"
    logger.info("Constructed URL: $url")

    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    try {
        client.newCall(request).execute().use { response ->
            logger.info("Response received with status code: ${response.code}")
            if (!response.isSuccessful) {
                logger.error("Unsuccessful response: ${response.code}")
                throw IOException("Unexpected response code: ${response.code}")
            }
            val bodyString = response.body?.string()
            if (bodyString == null) {
                logger.error("Response body is null")
                throw IOException("Empty response body")
            } else {
                logger.info("Response body length: ${bodyString.length} characters")
            }

            val document = Jsoup.parse(bodyString)
            val sampleTestDiv = document.select("div.sample-test")
            logger.info("Found ${sampleTestDiv.size} sample-test div(s)")

            if (sampleTestDiv.isEmpty()) {
                logger.error("No sample tests found in the page.")
                throw IOException("No sample tests found for problem $problemId in contest $contestId.")
            }

            val inputElements = sampleTestDiv.select("div.input pre")
            val outputElements = sampleTestDiv.select("div.output pre")
            logger.info("Found ${inputElements.size} input element(s) and ${outputElements.size} output element(s)")

            if (inputElements.size != outputElements.size) {
                logger.error("Mismatch between input and output count.")
                throw IOException("Mismatch in sample input and output counts.")
            }

            // Ensure output directory exists
            val outputDirPath = Paths.get(outputDirectory)
            if (!Files.exists(outputDirPath)) {
                logger.info("Output directory does not exist. Creating: $outputDirectory")
                Files.createDirectories(outputDirPath)
            } else {
                logger.info("Output directory exists: $outputDirectory")
            }

            for (i in inputElements.indices) {
                // For input, check if the <pre> element has children (like <div>) and join them with newline
                val inputPreElement = inputElements[i]
                val inputText = if (inputPreElement.children().isNotEmpty()) {
                    inputPreElement.children().joinToString("\n") { it.text().trim() }
                } else {
                    inputPreElement.text().trim()
                }

                // For output, we'll use the normal text extraction (assuming it's formatted correctly)
                val outputText = outputElements[i].text().trim()

                val inputFileName = "input-$contestId-$problemId-${i + 1}.txt"
                val outputFileName = "output-$contestId-$problemId-${i + 1}.txt"
                val inputFilePath = Paths.get(outputDirectory, inputFileName)
                val outputFilePath = Paths.get(outputDirectory, outputFileName)

                logger.info("Writing sample test #${i + 1} to files: $inputFileName and $outputFileName")
                Files.write(inputFilePath, inputText.toByteArray())
                Files.write(outputFilePath, outputText.toByteArray())
                VirtualFileManager.getInstance().asyncRefresh(null)
                logger.info("Successfully wrote $inputFileName and $outputFileName")
            }
        }
    } catch (e: Exception) {
        logger.error("Exception occurred during download", e)
        throw e
    }
}
