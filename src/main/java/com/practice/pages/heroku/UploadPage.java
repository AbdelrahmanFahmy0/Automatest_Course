package com.practice.pages.heroku;

import com.practice.drivers.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class UploadPage {

    Driver driver;

    // Constructor
    public UploadPage(Driver driver) {
        this.driver = driver;
    }

    // Locators
    private final By chooseFileButton = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By successUploadMessage = By.xpath("//h3[text() = 'File Uploaded!']");

    // Actions
    @Step("Navigate to the Heroku file upload page")
    public UploadPage navigate() {
        driver.browser().navigateTo(getProperty("herokuUploadUrl"));
        return this;
    }

    @Step("Choose file '{0}' for upload")
    public UploadPage chooseFile(String filePath) {
        driver.action().uploadFile(chooseFileButton, filePath);
        return this;
    }

    @Step("Click the upload button")
    public UploadPage clickUploadButton() {
        driver.action().click(uploadButton);
        return this;
    }

    // Assertions
    @Step("Verify the file upload completed successfully")
    public UploadPage checkFileIsUploaded() {
        driver.check().exists(successUploadMessage);
        return this;
    }
}