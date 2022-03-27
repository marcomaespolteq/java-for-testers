package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    @FindBy(className = "login")
    private WebElement login;
    @FindBy(className = "logout")
    private WebElement logout;
    @FindBy(id = "search_query_top")
    private WebElement searchQueryTop;
    @FindBy(css = "[name=submit_search]")
    private WebElement submitSearch;
    @FindBy(id = "contact-link")
    private WebElement contactLink;
    @FindBy(css = "h1")
    private WebElement header;
    protected final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openAuthenticationPage() {
        login.click();
    }

    public void signOut() {
        logout.click();
    }

    public void searchShop(String search_query) {
        searchQueryTop.clear();
        searchQueryTop.sendKeys(search_query);
        submitSearch.click();
    }

    public void openContactForm() {
        contactLink.click();
    }

    public boolean headerEqualsIgnoreCase(String header) {
        return this.header.getText().equalsIgnoreCase(header);
    }
}
