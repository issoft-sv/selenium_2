package task_60;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage_PageFactory {
    private WebDriver driver;

    public MainPage_PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='authorize']/div/a")
    private WebElement firstSigInButton;
    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@class='button m-green auth__enter']")
    private WebElement secondSignInButton;
    @FindBy(xpath = "//a[text()='Выйти']")
    private WebElement signOutButton;
    @FindBy(xpath = "//span[@class='uname']")
    private WebElement userNameDisplayed;

    public MainPage_PageFactory signIn (String username, String password) {
        firstSigInButton.click();
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        secondSignInButton.click();
        return this;
    }

    public MainPage_PageFactory signOut () {
        firstSigInButton.click();
        signOutButton.click();
        return this;
    }

    public String getUserNameDisplayed () {
        return userNameDisplayed.getText();
    }

    public String getFirstSignButtonText () {
        return firstSigInButton.getText();
    }


}
