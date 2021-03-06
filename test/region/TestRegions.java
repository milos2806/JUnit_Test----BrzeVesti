package region;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegionsPage;

public class TestRegions {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @BeforeClass
    public static void setUpClass() {

        System.out.println("@BeforeClass" + dateFormat.format(new Date()));

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("qa@cubes.rs");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys("cubesqa");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        loginButton.click();

        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("@AfterClass: " + dateFormat.format(new Date()));
        driver.quit();
    }

    @Before
    public void setUp() {

        WebElement regions = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Regions")));
        regions.click();
        System.out.println("@Before: " + dateFormat.format(new Date()));
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("@After: " + dateFormat.format(new Date()));

    }

    @Test
    public void testCreateRegion() {

        for (int i = 0; i < 3; i++) {

            RegionsPage regionsPage = new RegionsPage();
            regionsPage.createNewRegion(wait, driver);

//            WebElement titleField = driver.findElement(By.id("title"));
//            titleField.sendKeys(Helper.getRandomText());
            String expectedUrl = "http://bvtest.school.cubes.rs/admin/regions";
            String actualUrl = driver.getCurrentUrl();

            Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);

            String expectedTitle = "Brze vesti admin  | Regions ".replaceAll("\\s+", " ").trim();
            System.out.println("expected title: '" + expectedUrl + "'");
            String actualTitle = driver.getTitle();
            System.out.println("actual title: '" + expectedTitle + "'");

            Assert.assertEquals("Title doesn't match", expectedTitle, actualTitle);

        }

    }

    @Test
    public void testEditLastRegion() {
//        WebElement tBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
//        List<WebElement> rows = tBody.findElements(By.tagName("tr"));
        RegionsPage regionsPage = new RegionsPage();
        regionsPage.editLastRegion(driver, wait);

        String expectedUrl = "http://bvtest.school.cubes.rs/admin/regions";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);

        String expectedTitle = "Brze vesti admin  | Regions ".replaceAll("\\s+", " ").trim();
        System.out.println("expected title: '" + expectedUrl + "'");
        String actualTitle = driver.getTitle();
        System.out.println("actual title: '" + expectedTitle + "'");

        Assert.assertEquals("Title doesn't match", expectedTitle, actualTitle);

    }

    @Test
    public void testDeleteFirstRegion() {

        RegionsPage regionsPage = new RegionsPage();
        regionsPage.deleteFirstRegion(driver, wait);
    }

    @Test
    public void testDisableRegion() {
        RegionsPage regionsPage = new RegionsPage();
//        regionsPage.disableFirstRegion(wait);
        regionsPage.disableLastRegion(wait);
        //regionsPage.disableRandomRegion(wait);
    }

    @Test
    public void testEnableFirstRegion() {
        RegionsPage regionsPage = new RegionsPage();
        //regionsPage.enableFirstRegion(wait);
        regionsPage.enableLastRegion(wait);

    }

}
