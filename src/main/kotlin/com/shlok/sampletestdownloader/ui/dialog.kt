package com.shlok.sampletestdownloader.ui

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.*

class CodeforcesInputDialog : DialogWrapper(true) {

    private val contestField = JTextField(10)
    private val problemField = JTextField(10)

    init {
        init()  // Initializes the dialog components
        title = "Enter Codeforces Details"
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        panel.add(JLabel("Contest ID:"))
        panel.add(contestField)
        panel.add(Box.createVerticalStrut(10))

        panel.add(JLabel("Problem ID:"))
        panel.add(problemField)
        panel.add(Box.createVerticalStrut(10))

        return panel
    }

    fun getContestId(): String = contestField.text.trim()
    fun getProblemId(): String = problemField.text.trim()
}
