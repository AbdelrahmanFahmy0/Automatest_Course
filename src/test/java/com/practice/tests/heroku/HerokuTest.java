package com.practice.tests.heroku;

import com.practice.pages.heroku.CheckboxesPage;
import com.practice.pages.heroku.UploadPage;
import com.practice.template.TestCase;
import org.testng.annotations.Test;

public class HerokuTest extends TestCase {

    // Tests
    @Test
    public void checkStatusOfCheckboxes() {
        new CheckboxesPage(driver)
                .navigate()
                .checkCheckbox(1)
                .assertCheckboxIsChecked(1)
                .assertCheckboxIsChecked(2);
    }

    @Test
    public void checkFileUploading() {
        new UploadPage(driver)
                .navigate()
                .chooseFile("assets/example.png")
                .clickUploadButton()
                .checkFileIsUploaded();
    }
}