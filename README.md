# Codeforces Sample Downloader

## Overview

The **Codeforces Sample Downloader** is an IntelliJ IDEA plugin designed to simplify the process of downloading sample tests from Codeforces contest problems. Instead of manually copying and pasting sample inputs and outputs, this plugin automates the task by fetching, parsing, and saving them directly to your chosen directory. This streamlines your workflow and reduces the setup time required for competitive programming and coding practice.

## Why Is It Needed?

- **Time Efficiency:** Automates the tedious process of extracting sample tests, allowing you to focus on solving problems.
- **Reduced Errors:** Minimizes human error by automatically formatting and saving test cases.
- **Integrated Workflow:** Works directly within IntelliJ IDEA via a dedicated sidebar, ensuring you don’t have to leave your coding environment.
- **Customization:** Saves files using a clear naming convention (`input-<contestId>-<problemId>-<index>.txt` and `output-<contestId>-<problemId>-<index>.txt`), making it easy to organize and identify test cases.

## Features

- **Tool Window Integration:** The plugin adds a sidebar tool window to IntelliJ IDEA.
- **Elegant UI:** A clean, intuitive interface to input contest and problem IDs, and choose an output directory.
- **Optimized Downloading:** Uses a singleton OkHttpClient and streaming parsing with JSoup for fast, efficient network requests and HTML parsing.
- **Automatic File Generation:** Creates separate files for each sample test in your specified output directory.

