package com.practice.tests.heroku;

import com.practice.drivers.Driver;
import com.practice.pages.heroku.CheckboxesPage;
import com.practice.pages.heroku.UploadPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class HerokuTest {

    // Variables
    Driver driver;

    // Hooks
    @BeforeMethod
    public void setUp() {
        driver = new Driver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Tests
    @Test
    public void checkStatusOfCheckboxes() {
        new CheckboxesPage(driver.get())
                .navigate(getProperty("herokuCheckboxes"))
                .checkCheckbox(1)
                .assertCheckboxIsChecked(1)
                .assertCheckboxIsChecked(2);
    }

    @Test
    public void checkFileUploading() {
        new UploadPage(driver.get())
                .navigate(getProperty("herokuUpload"))
                .chooseFile("assets/example.png")
                .clickUploadButton()
                .checkFileIsUploaded();
    }
}