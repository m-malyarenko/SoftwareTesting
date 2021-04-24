package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    @FindBy(xpath = "//img[@id='user-icon']")
    private WebElement userIcon;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//button[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@id='user-name']")
    private WebElement userName;

    public Login(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getUserName() {
        return userName.getText().toUpperCase();
    }

    public void performLogin(String name, String password) {
        userIcon.click();
        userNameField.sendKeys(name);
        userPasswordField.sendKeys(password);
        loginButton.click();
    }
}
