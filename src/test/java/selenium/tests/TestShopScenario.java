package selenium.tests;

import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import selenium.lib.DriverFactory;
import selenium.pages.AuthenticationPage;
import selenium.pages.HomePage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestShopScenario {
    private WebDriver driver;

    private static Class<?> homePageClass;
    @Getter
    private static Object homePage;

//    @Getter
//    private static Class<?> authenticationPage;

//    @Getter
//    private HomePage homePage;
//
    @Getter
    private AuthenticationPage authenticationPage;

    @BeforeAll
    public static void setUpClasses() {
        try {
            homePageClass = Class.forName("selenium.pages.HomePage");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        try {
//            authenticationPage = Class.forName("selenium.pages.AuthenticationPage");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createBrowser(DriverFactory.Browser.CHROME);
        driver.manage().window().maximize();
        driver.get("https://techblog.polteq.com/testshop/");
        initPages();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private void initPages() {
//        homePage = new HomePage(driver);
        try {
            Constructor<?> homePageConstructor = homePageClass.getConstructor(WebDriver.class);
            homePage = homePageConstructor.newInstance(driver);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        authenticationPage = new AuthenticationPage(driver);
    }
}
