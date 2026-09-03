package com.practice.utils.report;

import com.practice.utils.core.OSUtils;
import com.practice.utils.core.TerminalUtils;
import com.practice.utils.logs.LogsManager;
import com.practice.utils.Indexes;
import org.jsoup.Jsoup;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AllureReportGenerator {

    /**
     * Generates an Allure 3 report using the repository's report configuration.
     * <p>
     * Windows uses {@code npx.cmd} because Java cannot directly launch the PowerShell
     * {@code npx} shim. Other supported platforms use {@code npx}.
     */
    public static void generateReport() {
        // npx --yes allure@3.16.0 generate test-output/reports/allure-results --config allurerc.mjs
        List<String> command = new ArrayList<>(List.of(
                OSUtils.getCurrentOS() == OSUtils.OS.WINDOWS ? "npx.cmd" : "npx",
                "--yes",
                "allure@" + resolveVersion(),
                "generate",
                Indexes.ALLURE_RESULTS_PATH,
                "--config",
                Indexes.ALLURE_CONFIGS_PATH
        ));
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
        LogsManager.info("Allure report generated successfully.");
    }

    /**
     * Opens the generated report entry page with the operating system's default browser.
     */
    public static void openReport() {
        Path reportPath = Path.of(Indexes.REPORT_PATH);
        switch (OSUtils.getCurrentOS()) {
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", "", reportPath.toString());
            case MAC, LINUX -> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }

    /**
     * Resolves the latest Allure 3 release version from GitHub's release redirect.
     *
     * @return the resolved Allure version without the leading {@code v}
     * @throws IllegalStateException when the version cannot be retrieved
     */
    private static String resolveVersion() {
        try {
            String url = Jsoup.connect("https://github.com/allure-framework/allure3/releases/latest")
                    .followRedirects(true).execute().url().toString();
            return url.split("/tag/v")[1];
        } catch (Exception e) {
            throw new IllegalStateException("Unable to resolve Allure version", e);
        }
    }
}