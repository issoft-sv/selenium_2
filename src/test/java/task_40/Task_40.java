package task_40;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task_40 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start () {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterEach
    public void stop () {
        driver.quit();
        driver = null;
    }
    @ParameterizedTest
    @CsvSource({"seleniumtests@tut.by, 123456789zxcvbn, Selenium Test", "seleniumtests2@tut.by, 123456789zxcvbn, Selenium Test"})
    void testLoginWithDifferentCreds (String username, String password, String expected){
        driver.get("https://www.tut.by/");
        driver.findElement(By.xpath("//*[@id='authorize']/div/a")).click();
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@class='button m-green auth__enter']")).click();
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='uname']"), expected));
    }
    @Test
    void testAlertBox () {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//div[@class='panel panel-primary'][1]//button")).click();
        driver.switchTo().alert().accept();
    }
    @Test
    void testConfirmBox () {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//div[@class='panel panel-primary'][2]//button")).click();
        driver.switchTo().alert().dismiss();
        assertEquals("You pressed Cancel!", driver.findElement(By.xpath("//p[@id='confirm-demo']")).getText());
    }
    @Test
    void testPromptBox () {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//div[@class='panel panel-primary'][3]//button")).click();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        assertEquals("You have entered 'Test' !", driver.findElement(By.xpath("//p[@id='prompt-demo']")).getText());
    }
    @Test
    void waitForUser () {
        driver.get("https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html");
        driver.findElement(By.xpath("//button[text()='Get New User']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='panel-body']//img")));
    }
    @Test
    void progressBar () {
        String s;
        driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
        driver.findElement(By.xpath("//button[@id='cricle-btn']")).click();
        while (true) {
            s = driver.findElement(By.xpath("//div[@class='percenttext']")).getText();
            if (Integer. parseInt(s.substring(0, s.length() - 1))>50) {
                driver.navigate().refresh();
                break;
            }
        }
        assertEquals("0%", s);
    }
    @Test
    void listFromTable () {
        List<String> finalList = new ArrayList<>();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
        driver.findElement(By.xpath("//select[@name='example_length']")).click();
        driver.findElement(By.xpath("//option[@value='10']")).click();
        List<WebElement> pagination =driver.findElements(By.xpath("//div[@id='example_paginate']/span/a"));
        for(int i = 0; i < pagination.size(); i++) {
            pagination.get(i).click();
            List<WebElement> rows = driver.findElement(By.xpath("//tbody")).findElements(By.xpath(".//tr"));
            List<WebElement> headings = driver.findElement(By.xpath("//thead")).findElements(By.xpath(".//th"));
            List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
            for (WebElement row : rows) {
                List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
                rowsWithColumns.add(rowWithColumns);
            }
            List<Map<String, WebElement>> rowsWithColumnsByHeadings = new ArrayList<Map<String, WebElement>>();
            Map<String, WebElement> rowByHeadings;
            for (List<WebElement> row : rowsWithColumns) {
                rowByHeadings = new HashMap<String, WebElement>();
                for (int j = 0; j < headings.size(); j++) {
                    String heading = headings.get(j).getText();
                    WebElement cell = row.get(j);
                    rowByHeadings.put(heading, cell);
                }
                rowsWithColumnsByHeadings.add(rowByHeadings);
            }
            for (Map<String, WebElement> row : rowsWithColumnsByHeadings) {
                int age = Integer.parseInt(row.get("Age").getText());
                String s = row.get("Salary").getText();
                int salary = Integer.parseInt((s.substring(1, s.length() - 2).replace(",", "")));
                if (age > 30 && salary < 300000) {
                    String result = row.get("Name").getText() + " " + row.get("Position").getText() + " " + row.get("Office").getText();
                    finalList.add(result);
                    System.out.println(result);
                }
            }
        }
    }
}
