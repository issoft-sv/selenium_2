package task_100;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainPage {
    static WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstSigInButton = By.xpath("//*[@id='authorize']/div/a");
    private By loginField = By.xpath("//input[@name='login']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By secondSignInButton = By.xpath("//input[@class='button m-green auth__enter']");
    private By signOutButton = By.xpath("//a[text()='Выйти']");
    private By userNameDisplayed = By.xpath("//span[@class='uname']");

    public MainPage signIn(String username, String password) {
        driver.findElement(firstSigInButton).click();
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(secondSignInButton).click();
        return this;
    }

    public MainPage signOut() {
        driver.findElement(firstSigInButton).click();
        driver.findElement(signOutButton).click();
        return this;
    }

    public String getUserNameDisplayed() {
        return driver.findElement(userNameDisplayed).getText();
    }

    public String getFirstSignButtonText() {
        return driver.findElement(firstSigInButton).getText();
    }

    public void makeMainPageScreenshot() {
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/test/screenshot/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

