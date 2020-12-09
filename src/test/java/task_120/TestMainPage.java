package task_120;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    public static final String username = "se.rgey";
    public static final String access_key = "389efb86-80a3-4108-91b2-780ac55b4199";
    public static final  String url = "https://" + username + ":" + access_key + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    @BeforeEach
    public void start () {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        EdgeOptions browserOptions = new EdgeOptions();
        browserOptions.setCapability("platformName", "Windows 10");
        browserOptions.setCapability("browserVersion", "latest");
        browserOptions.setCapability("sauce:options", sauceOptions);
        try {
            driver = new RemoteWebDriver(new URL(url), browserOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.tut.by/");
        mainPage = new MainPage(driver);
        mainPage.makeMainPageScreenshot();
    }
    @AfterEach
    public void stop () {
        driver.quit();
        driver = null;
    }
    @Test
    public void signInTest () {
        mainPage.signIn("seleniumtests@tut.by", "123456789zxcvbn");
        assertEquals("Selenium Test", mainPage.getUserNameDisplayed());
    }
    @Test
    public void signOutTest () {
        mainPage.signIn("seleniumtests@tut.by", "123456789zxcvbn");
        mainPage.signOut();
        assertEquals("Войти", mainPage.getFirstSignButtonText());
    }
}
