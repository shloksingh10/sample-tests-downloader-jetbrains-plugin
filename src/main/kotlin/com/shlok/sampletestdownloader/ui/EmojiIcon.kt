package com.shlok.sampletestdownloader.ui

import java.awt.Component
import java.awt.FontMetrics
import java.awt.Graphics
import javax.swing.Icon

/**
 * A simple Icon implementation that renders an emoji.
 */
class EmojiIcon(private val emoji: String, private val size: Int = 16) : Icon {
    override fun getIconWidth() = size
    override fun getIconHeight() = size

    override fun paintIcon(c: Component?, g: Graphics, x: Int, y: Int) {
        val fm: FontMetrics = g.getFontMetrics()
        // Draw the emoji string at the given position.
        g.drawString(emoji, x, y + fm.ascent)
    }
}
