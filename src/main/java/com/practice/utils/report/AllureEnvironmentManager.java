package com.practice.utils.report;

import com.google.common.collect.ImmutableMap;
import com.practice.utils.Indexes;

import java.io.File;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static com.practice.utils.dataReader.PropertyReader.getProperty;

public class AllureEnvironmentManager {

    /**
     * Writes the Allure environment file into the results directory so the report shows
     * runtime metadata such as Java version and browser.
     */
    public static void setEnvironmentVariables() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Java version:", getProperty("java.runtime.version"))
                        .put("Browser", getProperty("TargetBrowser"))
                        .put("Environment:", getProperty("Environment"))
                        //.put("URL", getProperty("baseUrlWeb"))
                        .build(), String.valueOf(Indexes.ALLURE_RESULTS_PATH) + File.separator
        );
    }
}