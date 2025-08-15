package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportPage {
    private final WebDriver driver;
    private final By table = By.id("reportTable");

    public ReportPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getHeaders() {
        WebElement thead = driver.findElement(table).findElement(By.tagName("thead"));
        List<WebElement> ths = thead.findElements(By.tagName("th"));
        List<String> headers = new ArrayList<>();
        for (WebElement th : ths) {
            headers.add(th.getText().trim());
        }
        return headers;
    }

    public List<Map<String, String>> getTableData() {
        WebElement tbody = driver.findElement(table).findElement(By.tagName("tbody"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        List<String> headers = getHeaders();
        List<Map<String, String>> data = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 0; i < headers.size() && i < tds.size(); i++) {
                map.put(headers.get(i), tds.get(i).getText().trim());
            }
            data.add(map);
        }
        return data;
    }
}
