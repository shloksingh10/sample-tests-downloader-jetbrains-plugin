package com.shlok.sampletestdownloader.ui

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.shlok.sampletestdownloader.utils.downloadSampleTests
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

/**
 * A cleaner UI panel for the Codeforces Sample Downloader tool window.
 */
class CodeforcesToolWindowPanel(private val project: Project) : JPanel(BorderLayout()) {

    private val contestField = JTextField(10)
    private val problemField = JTextField(10)
    private val outputDirectoryField = JTextField()
    private val browseButton = JButton("Browse...")
    private val downloadButton = JButton("Download Samples")

    init {
        // Create a vertical panel for inputs.
        val inputPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            preferredSize = Dimension(300, 150)
        }

        // Contest ID row
        val contestPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            add(JLabel("Contest ID: "))
            add(contestField)
        }
        inputPanel.add(contestPanel)

        // Problem ID row
        val problemPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            add(JLabel("Problem ID: "))
            add(problemField)
        }
        inputPanel.add(problemPanel)

        // Output Directory row with browse button
        val outputPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            add(JLabel("Output Directory: "))
            outputDirectoryField.preferredSize = Dimension(200, outputDirectoryField.preferredSize.height)
            add(outputDirectoryField)
            add(browseButton)
        }
        inputPanel.add(outputPanel)

        // Add the download button
        inputPanel.add(downloadButton)

        add(inputPanel, BorderLayout.NORTH)

        // Set default output directory from currently selected file/directory.
        updateOutputDirectory()

        // Listen for changes in selected file(s) to update the output directory dynamically.
        FileEditorManager.getInstance(project).addFileEditorManagerListener(object : FileEditorManagerListener {
            override fun selectionChanged(event: FileEditorManagerEvent) {
                updateOutputDirectory()
            }
        })

        // Browse button: opens a directory chooser.
        browseButton.addActionListener {
            val fileChooser = JFileChooser()
            fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            val result = fileChooser.showOpenDialog(this)
            if (result == JFileChooser.APPROVE_OPTION) {
                outputDirectoryField.text = fileChooser.selectedFile.absolutePath
            }
        }

        // Download button: validate inputs and trigger download.
        downloadButton.addActionListener {
            val contestId = contestField.text.trim()
            val problemId = problemField.text.trim()
            val outputDir = outputDirectoryField.text.trim()

            if (contestId.isEmpty() || problemId.isEmpty() || outputDir.isEmpty()) {
                Messages.showErrorDialog(project, "Please fill in all fields.", "Error")
                return@addActionListener
            }
            try {
                downloadSampleTests(contestId, problemId, outputDir)
                Messages.showInfoMessage(project, "Sample tests have been downloaded successfully! The IDE may take a moment to refresh the files.", "Success")
            } catch (ex: Exception) {
                Messages.showErrorDialog(project, "Failed to download sample tests: ${ex.message}", "Error")
            }
        }
    }

    private fun updateOutputDirectory() {
        val selectedFiles = FileEditorManager.getInstance(project).selectedFiles
        val newOutputDir = if (selectedFiles.isNotEmpty()) {
            val file = selectedFiles[0]
            if (file.isDirectory) file.path else file.parent.path
        } else {
            project.basePath ?: ""
        }
        outputDirectoryField.text = newOutputDir
    }
}
