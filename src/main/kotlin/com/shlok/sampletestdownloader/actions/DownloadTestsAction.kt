package com.shlok.sampletestdownloader.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.shlok.sampletestdownloader.ui.CodeforcesInputDialog
import com.shlok.sampletestdownloader.utils.downloadSampleTests

/**
 * An IntelliJ action that downloads Codeforces sample tests.
 */
class DownloadTestsAction : AnAction("Download Codeforces Samples") {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.project

        // Show the input dialog for contest and problem IDs.
        val dialog = CodeforcesInputDialog()
        if (!dialog.showAndGet()) return  // User cancelled

        val contestId = dialog.getContestId()
        val problemId = dialog.getProblemId()

        // Get the currently selected file or directory.
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val outputDirectory = if (virtualFile != null) {
            if (virtualFile.isDirectory) {
                virtualFile.path
            } else {
                virtualFile.parent.path
            }
        } else {
            // Fallback to project base path if no selection is found.
            project?.basePath ?: "."
        }

        ProgressManager.getInstance().runProcessWithProgressSynchronously({
            try {
                downloadSampleTests(contestId, problemId, outputDirectory)
            } catch (ex: Exception) {
                throw RuntimeException("Download failed: ${ex.message}")
            }
        }, "Downloading Codeforces Samples", false, project)

        Messages.showInfoMessage(
            project,
            "Sample tests downloaded successfully to: $outputDirectory",
            "Success"
        )
    }
}
