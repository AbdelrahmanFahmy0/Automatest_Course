package com.practice.tests.duckduckgo;

import com.practice.pages.duckduckgo.LandingPage;
import com.practice.template.TestCase;
import com.practice.utils.dataReader.JsonReader;
import org.testng.annotations.Test;

public class DuckGoTest extends TestCase {

    // Variables
    JsonReader duckGoData = new JsonReader("duckgo-data");

    // Tests
    @Test
    public void checkDuckGoPageTitle() {
        new LandingPage(driver)
                .navigate()
                .checkPageTitle("Google");
    }

    @Test
    public void checkDuckGoLogoIsDisplayed() {
        new LandingPage(driver)
                .navigate()
                .checkLogoIsDisplayed();
    }

    @Test
    public void checkFirstSearchResultURL() {
        new LandingPage(driver)
                .navigate()
                .search(duckGoData.getJsonData("results[0].searchKey"))
                .checkResultLink(1, duckGoData.getJsonData("results[0].url"));
    }

    @Test()
    public void checkForthSearchResultTitle() {
        new LandingPage(driver)
                .navigate()
                .search(duckGoData.getJsonData("results[1].searchKey"))
                .checkResultTitle(4, duckGoData.getJsonData("results[1].title"));
    }

    @Test
    public void checkSecondSearchResultURL() {
        new LandingPage(driver)
                .navigate()
                .search(duckGoData.getJsonData("results[2].searchKey"))
                .checkResultLinkContainsText(2, "https://www.linkedin.com");
    }
}