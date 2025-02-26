package com.shlok.sampletestdownloader.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

/**
 * Factory to create the Codeforces Sample Downloader tool window.
 */
class CodeforcesToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val panel = CodeforcesToolWindowPanel(project)
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
        // Set the emoji icon (📋) on the tool window stripe.
        toolWindow.setIcon(EmojiIcon("📋", 16))
    }
}
