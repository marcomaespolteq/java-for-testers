package selenium.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    @Getter
    private final WebDriver driver;

    @Getter
    private final Header header;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.header = new Header(driver);
        PageFactory.initElements(driver, this);
    }
}
