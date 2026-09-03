package com.practice.utils.core;

import com.practice.utils.logs.LogsManager;

import java.io.IOException;

public class TerminalUtils {

    /**
     * Executes a terminal command with the specified command parts.
     * Splits the command into parts and executes them in a new process.
     * Waits for the command to complete and logs any errors if the exit code is non-zero.
     *
     * @param commandParts the individual parts of the terminal command to execute
     *                     (e.g., "cmd", "/c", "dir" for Windows or "sh", "-c", "ls" for Unix)
     */
    public static void executeTerminalCommand(String... commandParts) {
        try {
            Process process = Runtime.getRuntime().exec(commandParts); //allure generate -o reports --single-file --clean
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LogsManager.error("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            LogsManager.error("Failed to execute terminal command: " + String.join(" ", commandParts), e.getMessage());
        }
    }
}