package com.practice.utils;

import com.practice.utils.logs.LogsManager;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.*;

public class FileUtils {

    private FileUtils() {
        // Prevent instantiation
    }

    /**
     * Renames a file by copying it to a new name and deleting the original.
     * The new file is created in the same directory as the original file.
     *
     * @param oldName the full path of the file to be renamed
     * @param newName the new name for the file (without path)
     */
    public static void renameFile(String oldName, String newName) {
        try {
            // Get the target file and its directory
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            // Create the new file object with the desired name
            File newFile = new File(targetDirectory + File.separator + newName);
            // Rename the file by copying and deleting the original
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                deleteQuietly(targetFile);
                LogsManager.info("Renamed file:", targetFile.getAbsolutePath(), "->", newFile.getAbsolutePath());
            } else {
                LogsManager.info("Rename skipped. File already has target name:", newFile.getAbsolutePath());
            }
        } catch (IOException e) {
            LogsManager.error("Failed to rename file:", oldName, "to", newName + ".", "Reason:", e.getMessage());
        }
    }

    /**
     * Creates a directory at the specified path if it does not already exist.
     * Creates any necessary parent directories as well.
     *
     * @param path the relative path of the directory to create
     */
    public static void createDirectory(String path) {
        try {
            File file = new File(Indexes.USER_DIR + path);
            if (!file.exists()) {
                file.mkdirs();
                LogsManager.info("Created directory:", file.getAbsolutePath());
            } else {
                LogsManager.debug("Directory already exists:", file.getAbsolutePath());
            }
        } catch (Exception e) {
            LogsManager.error("Failed to create directory:", path + ".", "Reason:", e.getMessage());
        }
    }

    /**
     * Forcefully deletes a file from the file system.
     * Attempts to delete even if the file is in use or requires special permissions.
     *
     * @param file the File object representing the file to delete
     */
    public static void forceFileDelete(File file) {
        try {
            forceDelete(file);
            LogsManager.info("Deleted file:", file.getAbsolutePath());
        } catch (IOException e) {
            LogsManager.error("Failed to delete file:", file.getAbsolutePath() + ".", "Reason:", e.getMessage());
        }
    }

    /**
     * Deletes a directory and all its contents quietly without throwing exceptions.
     * Silently handles any errors that occur during deletion.
     *
     * @param file the File object representing the directory to clean
     */
    public static void cleanDirectory(File file) {
        try {
            deleteQuietly(file);
        } catch (Exception e) {
            LogsManager.error("Failed to clean directory:", file.getAbsolutePath() + ".", "Reason:", e.getMessage());
        }
    }

    /**
     * Checks if a file exists in the downloads' directory.
     * Looks for the file in the src/test/resources/downloads/ path.
     *
     * @param fileName the name of the file to check for existence
     * @return true if the file exists, false otherwise
     */
    public static boolean isFileExists(String fileName) {
        File file = new File(Indexes.DOWNLOADS_PATH + fileName);
        return file.exists();
    }

    /**
     * Waits for a file to be downloaded by checking its existence multiple times with retries.
     * Checks the downloads directory repeatedly with 500ms intervals between retries.
     *
     * @param fileName        the name of the file to wait for
     * @param numberOfRetries the number of times to check for the file's existence
     * @return true if the file exists after checking, false if not found after all retries
     */
    public static boolean isFileExist(String fileName, int numberOfRetries) {
        boolean isFileExist = false;
        int i = 0;
        while (i < numberOfRetries) {
            try {
                isFileExist = (new File(Indexes.DOWNLOADS_PATH + fileName)).getAbsoluteFile().exists();
            } catch (Exception e) {
                LogsManager.error("Failed while checking downloaded file:", fileName + ".", "Reason:", e.getMessage());
            }
            if (!isFileExist) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    LogsManager.error(e.getMessage());
                }
            }
            i++;
        }
        if (!isFileExist) {
            LogsManager.warn("File not found after retries:", Indexes.DOWNLOADS_PATH + fileName);
        }
        return isFileExist;
    }
}