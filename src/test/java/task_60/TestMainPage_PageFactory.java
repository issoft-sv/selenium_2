package task_60;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMainPage_PageFactory {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage_PageFactory mainPage;

    @BeforeEach
    public void start () {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.tut.by/");
        mainPage = PageFactory.initElements(driver, MainPage_PageFactory.class);

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
