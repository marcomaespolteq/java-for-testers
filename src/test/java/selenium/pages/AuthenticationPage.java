package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.enums.Title;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class AuthenticationPage extends BasePage {
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "passwd")
    private WebElement password;
    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;
    @FindBy(id = "email_create")
    private WebElement emailCreate;
    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountButton;
    @FindBy(id = "uniform-id_gender1")
    private WebElement titleMr;
    @FindBy(id = "id_gender2")
    private WebElement titleMs;
    @FindBy(id = "id_gender3")
    private WebElement titleMrs;
    @FindBy(id = "customer_firstname")
    private WebElement firstName;
    @FindBy(id = "customer_lastname")
    private WebElement lastName;
    @FindBy(id = "days")
    private WebElement dayOfBirth;
    @FindBy(id = "months")
    private WebElement monthOfBirth;
    @FindBy(id = "years")
    private WebElement yearOfBirth;
    @FindBy(id = "newsletter")
    private WebElement newsletter;
    @FindBy(id = "optin")
    private WebElement optIn;
    @FindBy(id = "submitAccount")
    private WebElement submitAccount;
    @FindBy(className = "alert")
    private WebElement alert;

    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    private void setTitle(Title title) {
        switch (title) {
            case MR -> titleMr.click();
            case MS -> titleMs.click();
            case MRS -> titleMrs.click();
        }
    }

    private void setBirthDay(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        Select selectDay = new Select(dayOfBirth);
        Select selectMonth = new Select(monthOfBirth);
        Select selectYear = new Select(yearOfBirth);
        selectDay.selectByValue(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        selectMonth.selectByValue(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        selectYear.selectByValue(String.valueOf(calendar.get(Calendar.YEAR)));
    }

    public void signIn(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.signInButton.click();
    }

    public void createAnAccount(String email) {
        emailCreate.sendKeys(email);
        createAnAccountButton.click();
    }

    public void fillRegistrationFormAndSubmit(Title title, String firstName, String lastName, String password, Date birthday, boolean newsletter, boolean optIn) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(titleMr));
        setTitle(title);
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.password.sendKeys(password);
        setBirthDay(birthday);
        if (newsletter) {
            this.newsletter.click();
        }
        if (optIn) {
            this.optIn.click();
        }
        submitAccount.click();
    }

    public String getAlert() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(alert));
        return alert.getText();
    }
}
