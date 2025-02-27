# Codeforces Sample Downloader

## Overview

The **Codeforces Sample Tests Downloader** is an IntelliJ IDEA plugin designed to simplify the process of downloading sample tests from Codeforces contest problems. Instead of manually copying and pasting sample inputs and outputs, this plugin automates the task by fetching, parsing, and saving them directly to your chosen directory. This streamlines your workflow and reduces the setup time required for competitive programming and coding practice.

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

## Download and Installation

### Installing from the JetBrains Plugin Marketplace

1. **Open Your IDE:**  
   Launch your JetBrains IDE (e.g., IntelliJ IDEA, PyCharm, WebStorm, etc.).

2. **Open the Plugins Settings:**  
   Navigate to **File > Settings > Plugins** (or **Preferences > Plugins** on macOS).

3. **Search for the Plugin:**  
   Go to the **Marketplace** tab and search for **"Codeforces Sample Tests Downloader"**.

4. **Install the Plugin:**  
   Click **Install**. Once installed, restart your IDE when prompted.

## How to Use

1. **Open the Tool Window:**
    - After installation, locate the **Codeforces Tests Loader** tool window on the right-hand side.
    - If not visible, open it via **View > Tool Windows > Codeforces Tests Loader**.

2. **Enter Contest and Problem Details:**
    - **Contest ID:** Enter the contest identifier (e.g., `165`).
    - **Problem ID:** Enter the problem identifier (e.g., `A`).
    - **Output Directory:**  
      This field is auto-populated based on the directory of the file you last opened or selected in the IDE. If needed, you can change it using the **Browse...** button.

3. **Download Sample Tests:**
    - Click the **Download Samples** button.
    - The plugin will fetch the problem page from Codeforces, parse the sample tests, and save them as separate files in the specified directory.
    - Files are named in the format:
        - `input-<contestId>-<problemId>-<index>.txt`
        - `output-<contestId>-<problemId>-<index>.txt`

4. **Verify Download:**
    - Check the output directory in your Project view to verify the downloaded files. Keep in mind that IDEs don't refresh files instantly, so it may take a few seconds for them to appear.

## Troubleshooting

- **Files Not Appearing:**  
  If you don’t see the downloaded files immediately, try manually refreshing the project view or verify that the correct output directory is selected.

- **Download Errors:**  
  Ensure you have an active internet connection and that the contest/problem IDs are valid. Check the IDE log via **Help > Show Log** for more details.

## Feedback and Contributions

Your feedback and contributions are welcome! Please open an issue or submit a pull request on our [GitHub repository](https://github.com/yourusername/CodeforcesSampleDownloader) if you encounter any problems or have suggestions for improvements.

## License

This project is licensed under the [MIT License](LICENSE).
