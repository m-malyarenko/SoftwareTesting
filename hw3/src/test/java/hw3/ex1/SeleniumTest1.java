package hw3.ex1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest1 {

    private WebDriver webDriver;

    /* References -----------------------------------------------------------*/

    private String TEST_PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    private String BROWSER_TITLE = "Home Page";
    private String USER_NAME = "ROMAN IOVLEV";
    private String[] TOP_MENU_TITLES = {"HOME", "CONTACT FORM", "SERVICE", "METALS &AMP; COLORS"};
    private String[] BENEFIT_CAPTIONS = {
        "To include good practices\nand ideas from successful\nEPAM project",
        "To be flexible and\ncustomizable ",
        "To be multiplatform ",
        "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
    };
    private String MAIN_HEADER_TITLE = "EPAM FRAMEWORK WISHES…";
    private String MAIN_HEADER_TEXT = 
        "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
        "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
        "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
        "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
        "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";
    private String SUB_HEADER_TEXT = "JDI GITHUB";
    private String SUB_HEADER_LINK = "https://github.com/epam/JDI";

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
        /* 16. Close Browser */
        webDriver.quit();
    }

    @Test
    public void openedPageUrlTest() {
        String actualPageUrl = webDriver.getCurrentUrl();

        /* 1. Open test site by URL */
        Assert.assertEquals(actualPageUrl, TEST_PAGE_URL);
    }

    @Test
    public void browserTitleTest() {
        String actualBrowserTitle = webDriver.getTitle();

        /* 2. Assert Browser title */
        Assert.assertEquals(actualBrowserTitle, BROWSER_TITLE);
    }

    @Test
    public void loginTest() {
        /* 3. Perform login */
        webDriver.findElement(By.xpath("//img[@id='user-icon']")).click(); // Goto login menu
        webDriver.findElement(By.xpath("//input[@id='name']")).sendKeys("Roman");
        webDriver.findElement(By.xpath("//input[@id='password']")).sendKeys("Jdi1234");
        webDriver.findElement(By.xpath("//button[@id='login-button']")).click(); // Perform login

        WebElement username = webDriver.findElement(By.xpath("//span[@id='user-name']"));
        String actualUsername = username.getAttribute("innerHTML");

        /* 
         * 4. Assert User name in the left-top
         * side of screen that user is loggined 
         */
        Assert.assertEquals(actualUsername.toUpperCase(), USER_NAME);

        /* 5. Assert Browser title */
        Assert.assertEquals(webDriver.getTitle(), BROWSER_TITLE);
    }

    @Test
    public void topMenuTitlesTest() {
        /* 
         * 6. Assert that there are 4 items on the header
         * section are displayed and they have proper texts
         */
        String path = "//div[starts-with(@class,'uui-header')]/nav/ul[1]/li/a";
        List<WebElement> titles = webDriver.findElements(By.xpath(path));
        Assert.assertTrue(titles.size() == 4);
        
        for (WebElement we : titles) {
            Assert.assertTrue(we.isDisplayed());
        }

        for (int i = 0; i < TOP_MENU_TITLES.length; i++) {
            String actualTitle = titles.get(i).getAttribute("innerHTML");
            actualTitle = actualTitle.replaceAll("<.*>", "")
                                    .trim()
                                    .toUpperCase();
            
            Assert.assertEquals(actualTitle, TOP_MENU_TITLES[i]);
        }
    }

    @Test
    public void indexPageImagesTest() {
        /* 7. Assert that there are 4 images on the Index Page and they are displayed */
        List<WebElement> icons = webDriver.findElements(By.xpath("//div[@class='benefit-icon']"));
        Assert.assertTrue(icons.size() == 4);

        for (WebElement we : icons) {
            Assert.assertTrue(we.isDisplayed());
        }
    }

    @Test
    public void imagesCaptionsTest() {
        /* 8. Assert that there are 4 texts on the Index Page under icons and they have proper text */
        List<WebElement> captions = webDriver.findElements(By.xpath("//span[@class='benefit-txt']"));
        Assert.assertTrue(captions.size() == 4);

        for (WebElement we : captions) {
            Assert.assertTrue(we.isDisplayed());
        }

        for (int i = 0; i < captions.size(); i++) {
            String actualCaption = captions.get(i).getAttribute("innerHTML").replaceAll("<br>", "\n");

            Assert.assertEquals(actualCaption, BENEFIT_CAPTIONS[i]);
        }
    }

    @Test
    public void mainHeadersTextTest() {
        /* 9. Assert a text of the main headers */
        WebElement mainTitle = webDriver.findElement(By.xpath("//h3[@name='main-title']"));
        String actualMainTitle = mainTitle.getAttribute("innerHTML").toUpperCase();

        WebElement mainText = webDriver.findElement(By.xpath("//p[@name='jdi-text']"));
        String actualMainText = mainText.getAttribute("innerHTML")
                                        .replaceAll("(\\s+)|(\n)", " ")
                                        .toUpperCase();

        Assert.assertTrue(mainTitle.isDisplayed());
        Assert.assertTrue(mainText.isDisplayed());

        Assert.assertEquals(actualMainTitle, MAIN_HEADER_TITLE);
        Assert.assertEquals(actualMainText, MAIN_HEADER_TEXT);
    }

    @Test
    public void iframeTest() {
        /* 10. Assert that there is the iframe in the center of page */
        WebElement centerIframe = webDriver.findElement(By.xpath("//iframe[@id='second_frame']"));

        Assert.assertTrue(centerIframe.isDisplayed());

        /* 
         * 11. Switch to the iframe and check that
         * there is Epam logo in the left top conner of iframe
         */
        webDriver.switchTo().frame(centerIframe);
        WebElement epamLogo = webDriver.findElement(By.xpath("//img[@id='epam-logo']"));

        Assert.assertTrue(epamLogo.isDisplayed());

        /* 12. Switch to original window back */
        webDriver.switchTo().parentFrame();
    }

    @Test
    public void subHeaderTest() {
        /* 13. Assert a text of the sub header */
        /* 14. Assert that JDI GITHUB is a link and has a proper URL */
        WebElement subHeader = webDriver.findElement(By.xpath("//h3[@class='text-center']/a"));
        String actualSubHeaderText = subHeader.getAttribute("innerHTML").toUpperCase();
        String actualSubHeaderLink = subHeader.getAttribute("href");

        Assert.assertEquals(actualSubHeaderText, SUB_HEADER_TEXT);
        Assert.assertEquals(actualSubHeaderLink, SUB_HEADER_LINK);
    }

    @Test
    public void leftSectionTest() {
        /* 15. Assert that there is Left Section */
        WebElement leftSidebar = webDriver.findElement(By.xpath("//div[@name='navigation-sidebar']"));

        Assert.assertTrue(leftSidebar.isDisplayed());
    }

    @Test
    public void footerTest() {
        /* 16. Assert that there is Footer */
        WebElement footer = webDriver.findElement(By.xpath("//div[@class='footer-bg']"));

        Assert.assertTrue(footer.isDisplayed());
    }
}
