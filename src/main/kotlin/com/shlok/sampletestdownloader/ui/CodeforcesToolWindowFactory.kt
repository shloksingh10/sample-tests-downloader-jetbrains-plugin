package com.shlok.sampletestdownloader.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.util.IconLoader
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

        // Load the SVG icon from resources
        val icon = IconLoader.getIcon("/icons/logo.svg", CodeforcesToolWindowFactory::class.java)
        toolWindow.setIcon(icon)

        // Set the stripe title which is shown as tooltip on hover
        toolWindow.setStripeTitle("Codeforces Tests Downloader")
    }
}
