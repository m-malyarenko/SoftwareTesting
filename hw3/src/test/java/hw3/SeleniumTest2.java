package hw3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest2 {
    
    private WebDriver webDriver;

    /* References -----------------------------------------------------------*/

    private final String TEST_PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    private final String BROWSER_TITLE = "HOME PAGE";
    private final String USER_NAME = "ROMAN IOVLEV";
    private final int DROPDOWN_OPTIONS_NUM = 9;
    private final String[] DROPDOWN_MENU_OPTIONS = {
        "SUPPORT",
        "DATES",
        "SEARCH",
        "COMPLEX TABLE",
        "SIMPLE TABLE",
        "USER TABLE",
        "TABLE WITH PAGES",
        "DIFFERENT ELEMENTS",
        "PERFORMANCE"
    };
    private final String[] SIDE_DROPDOWN_MENU_OPTIONS = {
        "SUPPORT",
        "DATES",
        "COMPLEX TABLE",
        "SIMPLE TABLE",
        "SEARCH",
        "USER TABLE",
        "TABLE WITH PAGES",
        "DIFFERENT ELEMENTS",
        "PERFORMANCE"
    };
    private final int CHECKBOX_NUM = 4;
    private final int RADIOBUTTON_NUM = 4;

    /* Tests ----------------------------------------------------------------*/

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(TEST_PAGE_URL); 
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public void shutDown() {
        webDriver.quit();
    }

    @Test
    public void openedPageUrlTest() {
        String actualPageUrl = webDriver.getCurrentUrl();

        Assert.assertEquals(actualPageUrl, TEST_PAGE_URL);
    }

    @Test
    public void browserTitleTest() {
        String actualBrowserTitle = webDriver.getTitle().toUpperCase();

        Assert.assertEquals(actualBrowserTitle, BROWSER_TITLE);
    }

    @Test
    public void loginTest() {
        webDriver.findElement(By.xpath("//img[@id='user-icon']")).click(); // Goto login menu
        webDriver.findElement(By.xpath("//input[@id='name']")).sendKeys("Roman");
        webDriver.findElement(By.xpath("//input[@id='password']")).sendKeys("Jdi1234");
        webDriver.findElement(By.xpath("//button[@id='login-button']")).click(); // Perform login

        WebElement username = webDriver.findElement(By.xpath("//span[@id='user-name']"));
        String actualUsername = username.getAttribute("innerHTML");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUsername.toUpperCase(), USER_NAME);
        softAssert.assertTrue(username.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void dropdownMenuTest() {
        String menuPath = "//div[starts-with(@class, uui-header)]//li[@class='dropdown']";
        WebElement dropdownMenu = webDriver.findElement(By.xpath(menuPath));
        dropdownMenu.click();
        List<WebElement> dropdownMenuOptions = dropdownMenu.findElements(By.tagName("li"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dropdownMenuOptions.size() == DROPDOWN_OPTIONS_NUM);

        for (int i = 0; i < dropdownMenuOptions.size(); i++) {
            WebElement option = dropdownMenuOptions.get(i).findElement(By.tagName("a"));

            String actualOptionName = option.getAttribute("innerHTML")
                                            .toUpperCase()
                                            .trim();

            softAssert.assertTrue(dropdownMenuOptions.get(i).isDisplayed());                                       
            softAssert.assertEquals(actualOptionName, DROPDOWN_MENU_OPTIONS[i]);
        }

        softAssert.assertAll();
    }

    @Test
    public void sideDropdownMenuTest() {
        String menuPath = "//ul[@class='sidebar-menu']/li[@class='menu-title']";
        WebElement dropdownMenu = webDriver.findElement(By.xpath(menuPath));
        dropdownMenu.click();
        List<WebElement> dropdownMenuOptions = dropdownMenu.findElements(By.tagName("li"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dropdownMenuOptions.size() == DROPDOWN_OPTIONS_NUM);

        for (int i = 0; i < dropdownMenuOptions.size(); i++) {
            WebElement option = dropdownMenuOptions.get(i).findElement(By.tagName("span"));

            String actualOptionName = option.getAttribute("innerHTML")
                                            .toUpperCase()
                                            .trim();
                                            
            softAssert.assertTrue(dropdownMenuOptions.get(i).isDisplayed());                                       
            softAssert.assertEquals(actualOptionName, SIDE_DROPDOWN_MENU_OPTIONS[i]);
        }

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void difElementsPageTest() {
        String menuPath = "//div[starts-with(@class, uui-header)]//li[@class='dropdown']";
        WebElement dropdownMenu = webDriver.findElement(By.xpath(menuPath));
        dropdownMenu.click();
        dropdownMenu.findElement(By.xpath("//a[text()='Different elements']")).click();
    }

    @Test(dependsOnMethods = {"difElementsPageTest"})
    public void elementsTest() {
        String checkboxesPath = "//input[@type='checkbox']";
        List<WebElement> checkboxes = webDriver.findElements(By.xpath(checkboxesPath));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(checkboxes.size() == CHECKBOX_NUM);
        for (WebElement we : checkboxes) {
            softAssert.assertTrue(we.isDisplayed());
        }

        String radiobuttonsPath = "//input[@type='radio']";
        List<WebElement> radiobuttons = webDriver.findElements(By.xpath(radiobuttonsPath));

        softAssert.assertTrue(radiobuttons.size() == RADIOBUTTON_NUM);
        for (WebElement we : radiobuttons) {
            softAssert.assertTrue(we.isDisplayed());
        }

        String selectboxPath = "//div[@class='colors']/select";
        WebElement selectbox = webDriver.findElement(By.xpath(selectboxPath));

        softAssert.assertTrue(selectbox.isDisplayed());

        softAssert.assertTrue(webDriver.findElement(By.xpath("//button[@name='Default Button']")).isDisplayed());
        softAssert.assertTrue(webDriver.findElement(By.xpath("//input[@value='Button']")).isDisplayed());

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"difElementsPageTest"})
    public void sideSectionsTest() {
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@name='navigation-sidebar']")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@name='log-sidebar']")).isDisplayed());
    }

    @Test(dependsOnMethods = {"difElementsPageTest"})
    public void checkboxTest() {
        webDriver.navigate().refresh();

        WebElement checkbox1 = webDriver.findElement(By.xpath("//div[@class='checkbox-row'][1]/label[1]/input"));
        checkbox1.click();

        WebElement checkbox2 = webDriver.findElement(By.xpath("//div[@class='checkbox-row'][1]/label[3]/input"));
        checkbox2.click();

        SoftAssert softAssert = new SoftAssert();

        WebElement checkbox1Log = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li[2]"));
        WebElement checkbox2Log = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li[1]"));

        String checkbox1Status = checkbox1Log.getAttribute("innerHTML");
        String checkbox2Status = checkbox2Log.getAttribute("innerHTML");

        softAssert.assertTrue(checkbox1.isSelected());
        softAssert.assertTrue(checkbox2.isSelected());
        softAssert.assertTrue(checkbox1Log.isDisplayed());
        softAssert.assertTrue(checkbox1Status.contains("Water") && checkbox1Status.contains("true"));
        softAssert.assertTrue(checkbox2Log.isDisplayed());
        softAssert.assertTrue(checkbox2Status.contains("Wind") && checkbox2Status.contains("true"));

        checkbox1.click();
        checkbox2.click();

        checkbox1Log = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li[2]"));
        checkbox2Log = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li[1]"));

        checkbox1Status = checkbox1Log.getAttribute("innerHTML");
        checkbox2Status = checkbox2Log.getAttribute("innerHTML");

        softAssert.assertFalse(checkbox1.isSelected());
        softAssert.assertFalse(checkbox2.isSelected());
        softAssert.assertTrue(checkbox1Log.isDisplayed());
        softAssert.assertTrue(checkbox1Status.contains("Water") && checkbox1Status.contains("false"));
        softAssert.assertTrue(checkbox2Log.isDisplayed());
        softAssert.assertTrue(checkbox2Status.contains("Wind") && checkbox2Status.contains("false"));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"difElementsPageTest"})
    public void radiobuttonTest() {
        webDriver.navigate().refresh();

        WebElement radiobutton1 = webDriver.findElement(By.xpath("//div[@class='checkbox-row'][2]/label[4]/input"));
        radiobutton1.click();

        WebElement radiobutton1Log = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li[1]"));
        String radiobutton1Status = radiobutton1Log.getAttribute("innerHTML");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(radiobutton1Log.isDisplayed());
        softAssert.assertTrue(radiobutton1Status.contains("metal") && radiobutton1Status.contains("Selen"));
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"difElementsPageTest"})
    public void selectbarTest() {
        webDriver.navigate().refresh();
        
        WebElement selectbar = webDriver.findElement(By.xpath("//div[@class='colors']"));
        selectbar.click();
        WebElement option = selectbar.findElement(By.xpath(".//option[text()='Yellow']"));
        option.click();

        WebElement selectbarLog = webDriver.findElement(By.xpath("//ul[starts-with(@class, 'panel-body-list')]/li[1]"));
        String selectbarState = selectbarLog.getAttribute("innerHTML");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(option.isSelected());
        softAssert.assertTrue(selectbarLog.isDisplayed());
        softAssert.assertTrue(selectbarState.contains("Colors") && selectbarState.contains("Yellow"));
        softAssert.assertAll();
    }
}