package com.practice.tests.heroku;

import com.practice.pages.heroku.CheckboxesPage;
import com.practice.pages.heroku.UploadPage;
import com.practice.template.TestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

@Epic("Automatest Course")
@Feature("Heroku Practice Site")
@Owner("Abdelrahman Fahmy")
public class HerokuTest extends TestCase {

    // Tests
    @Test(description = "Checking a checkbox also checks its dependent checkbox")
    @Description("Verifies that checking checkbox 1 results in both checkbox 1 and checkbox 2 being checked")
    @Severity(SeverityLevel.NORMAL)
    public void checkStatusOfCheckboxes() {
        new CheckboxesPage(driver)
                .navigate()
                .checkCheckbox(1)
                .assertCheckboxIsChecked(1)
                .assertCheckboxIsChecked(2);
    }

    @Test(description = "Uploading a file completes successfully")
    @Description("Verifies that choosing a file and clicking upload results in the file being successfully uploaded")
    @Severity(SeverityLevel.CRITICAL)
    public void checkFileUploading() {
        new UploadPage(driver)
                .navigate()
                .chooseFile("assets/example.png")
                .clickUploadButton()
                .checkFileIsUploaded();
    }
}