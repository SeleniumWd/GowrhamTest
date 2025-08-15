package com.example.steps;

import com.example.pages.ReportPage;
import com.example.utils.CsvUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ReportSteps {

    private WebDriver driver;
    private ReportPage reportPage;

    public ReportSteps() {
        this.driver = Hooks.driver;
    }

    @Given("I open the local integration report")
    public void iOpenTheLocalIntegrationReport() throws Exception {
        // Load report.html from resources
        URL resource = getClass().getClassLoader().getResource("report.html");
        if (resource == null) throw new IllegalStateException("report.html not found in resources");
        String path = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        driver.get("file://" + path);
        reportPage = new ReportPage(driver);
    }

    @Then("the table should match the expected merged CSV")
    public void theTableShouldMatchTheExpectedMergedCSV() {
        // Actual from UI
        List<Map<String, String>> uiData = reportPage.getTableData();

        // Expected from CSV
        List<Map<String, String>> expected = CsvUtils.readCsvFromResources("data/expected_merged.csv");

        // Basic size check
        Assertions.assertEquals(expected.size(), uiData.size(), "Row count mismatch");

        // Column order must match the CSV headers
        // Compare each row (order-sensitive). For robust comparison, you could sort by UserID.
        for (int i = 0; i < expected.size(); i++) {
            Map<String, String> expRow = expected.get(i);
            Map<String, String> uiRow = uiData.get(i);
            Assertions.assertEquals(expRow, uiRow, "Row mismatch at index " + i);
        }
    }
}
