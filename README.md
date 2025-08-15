# IntegrationReportBDD (Java + Selenium + Cucumber BDD)

This is a **runnable** sample project that demonstrates integration testing of a consolidated **Agent/Employee Report** generated from **User** and **Employee** modules.

It opens a **local HTML report** (pre-generated with 200 rows) and validates that table rows match the **expected merged CSV**.

## Tech Stack
- Java 17
- Maven
- Selenium 4 (Chrome)
- WebDriverManager (auto-manages ChromeDriver)
- Cucumber (BDD) + JUnit 5
- Apache Commons CSV

## Prerequisites
- Chrome installed (latest stable)
- Java 17+ and Maven installed
- File system access allowed for Chrome (default)

## How to Run
```bash
mvn clean test
```

The test opens a local file:
- `src/test/resources/report.html`

and verifies it against:
- `src/test/resources/data/expected_merged.csv`

## What It Tests
- Loads local report table with columns:
  `UserID, Name, Email, Role, CreatedDate, Status, Department, Manager, JoiningDate, Salary`
- Extracts **all table rows** from the page
- Loads expected merged data from CSV
- Asserts that the two datasets match exactly (row-by-row & column-by-column)

## Project Structure
```
src/
  main/java/com/example/pages/ReportPage.java    # Page Object to read table
  main/java/com/example/utils/CsvUtils.java      # CSV reader utility
  test/java/com/example/steps/Hooks.java         # WebDriver setup/teardown
  test/java/com/example/steps/ReportSteps.java   # Step definitions
  test/java/com/example/runner/CucumberRunner.java
  test/resources/features/report_generation.feature
  test/resources/report.html
  test/resources/data/users.csv
  test/resources/data/employees.csv
  test/resources/data/expected_merged.csv
```

## Recording a Demo
You can record with any screen recorder while running:
```
mvn test -Dcucumber.filter.tags="@smoke"
```
(There is a `@smoke` tag on the main scenario.)

## Notes
- The page is local and static to make the framework fully self-contained.
- You can swap `report.html` with a real application URL and reuse the steps.
- To change dataset size, regenerate `expected_merged.csv` and sync the HTML table.
```

