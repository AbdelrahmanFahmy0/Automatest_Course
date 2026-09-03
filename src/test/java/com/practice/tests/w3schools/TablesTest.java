package com.practice.tests.w3schools;

import com.practice.pages.w3schools.TablesPage;
import com.practice.template.TestCase;
import com.practice.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

@Epic("Automatest Course")
@Feature("W3Schools Tables")
@Owner("Abdelrahman Fahmy")
public class TablesTest extends TestCase {

    // Variables
    JsonReader w3Data = new JsonReader("w3schools-data");

    // Tests
    @Test(description = "Company row displays its correct country")
    @Description("Verifies that the table row for the configured company shows the expected country")
    @Severity(SeverityLevel.NORMAL)
    public void checkCountryOfCompany() {
        new TablesPage(driver)
                .navigate()
                .checkCountryOfCompany(w3Data.getJsonData("company"), w3Data.getJsonData("country"));
    }
}