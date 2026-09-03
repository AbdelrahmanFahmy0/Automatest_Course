package com.practice.tests.duckduckgo;

import com.practice.pages.duckduckgo.LandingPage;
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
@Feature("DuckDuckGo Search")
@Owner("Abdelrahman Fahmy")
public class DuckGoTest extends TestCase {

    // Variables
    JsonReader duckGoData = new JsonReader("duckgo-data");

    // Tests
    @Test(description = "Landing page title matches expected value")
    @Description("Verifies that the DuckDuckGo landing page title equals the expected title")
    @Severity(SeverityLevel.CRITICAL)
    public void checkDuckGoPageTitle() {
        new LandingPage(driver)
                .navigate()
                .checkPageTitle("Google");
    }

    @Test(description = "Landing page logo is displayed")
    @Description("Verifies that the DuckDuckGo logo is visible on the landing page")
    @Severity(SeverityLevel.MINOR)
    public void checkDuckGoLogoIsDisplayed() {
        new LandingPage(driver)
                .navigate()
                .checkLogoIsDisplayed();
    }

    @Test(description = "First search result links to the expected URL")
    @Description("Verifies that the first search result link matches the expected URL for the configured search key")
    @Severity(SeverityLevel.CRITICAL)
    public void checkFirstSearchResultURL() {
        new LandingPage(driver)
                .navigate()
                .search(duckGoData.getJsonData("results[0].searchKey"))
                .checkResultLink(1, duckGoData.getJsonData("results[0].url"));
    }

    @Test(description = "Fourth search result title matches expected value")
    @Description("Verifies that the fourth search result title matches the expected title for the configured search key")
    @Severity(SeverityLevel.NORMAL)
    public void checkForthSearchResultTitle() {
        new LandingPage(driver)
                .navigate()
                .search(duckGoData.getJsonData("results[1].searchKey"))
                .checkResultTitle(4, duckGoData.getJsonData("results[1].title"));
    }

    @Test(description = "Second search result link contains expected text")
    @Description("Verifies that the second search result link contains the expected LinkedIn URL text")
    @Severity(SeverityLevel.NORMAL)
    public void checkSecondSearchResultURL() {
        new LandingPage(driver)
                .navigate()
                .search(duckGoData.getJsonData("results[2].searchKey"))
                .checkResultLinkContainsText(2, "https://www.linkedin.com");
    }
}