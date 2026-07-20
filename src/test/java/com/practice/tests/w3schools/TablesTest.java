package com.practice.tests.w3schools;

import com.practice.pages.w3schools.TablesPage;
import com.practice.template.TestCase;
import com.practice.utils.dataReader.JsonReader;
import org.testng.annotations.Test;

public class TablesTest extends TestCase {

    // Variables
    JsonReader w3Data = new JsonReader("w3schools-data");

    // Tests
    @Test
    public void checkCountryOfCompany() {
        new TablesPage(driver)
                .navigate()
                .checkCountryOfCompany(w3Data.getJsonData("company"), w3Data.getJsonData("country"));
    }
}