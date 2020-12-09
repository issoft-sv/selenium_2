package task_100;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Listeners(Listener.class)
public class TestMainPage {
        private WebDriver driver;
        private WebDriverWait wait;
        private MainPage mainPage;

        @BeforeTest
        public void start () {
            driver = Driver.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://www.tut.by/");
            mainPage = new MainPage(driver);
            mainPage.makeMainPageScreenshot();

        }
        @AfterTest
        public void stop () {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
            driver = null;
        }

        @Feature("Login")
        @Description("Verify user can login to the site")
        @TestCaseId("1234")
        @Test
        public void signInTest () {
            mainPage.signIn("seleniumtests@tut.by", "123456789zxcvbn");
            assertEquals("Selenium Test", mainPage.getUserNameDisplayed());
        }

        @Feature("Login")
        @Description ("Verify user can logout from the site")
        @TestCaseId("2345")
        @Test
        public void signOutTest () {
            mainPage.signIn("seleniumtests@tut.by", "123456789zxcvbn");
            mainPage.signOut();
            assertEquals("Войти", mainPage.getFirstSignButtonText());
        }
    }
