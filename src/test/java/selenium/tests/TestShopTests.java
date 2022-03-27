package selenium.tests;

import lombok.extern.slf4j.Slf4j;
import net.andreinc.mockneat.unit.time.LocalDates;
import net.andreinc.mockneat.unit.types.Bools;
import net.andreinc.mockneat.unit.types.Ints;
import net.andreinc.mockneat.unit.user.Emails;
import net.andreinc.mockneat.unit.user.Names;
import net.andreinc.mockneat.unit.user.Passwords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.enums.Title;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestShopTests extends TestShopScenario {
    @DisplayName("Test if we can login")
    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            email,              password,   expected h1
            tester@test.com,    1qazxsw2,   My account
            wrong@mail.com,     12345678,   Authentication
            """)
    public void loginTest(String email, String password, String header) {
        getHomePage().getHeader().openAuthenticationPage();
        getAuthenticationPage().signIn(email, password);
        assertTrue(getAuthenticationPage().getHeader().headerEqualsIgnoreCase(header));
    }

    @Test
    @DisplayName("Register with random account")
    public void registerTest() {
        String email = Emails.emails().get();
        Title title = Title.getByValue(String.valueOf(Ints.ints().range(1, 4).get()));
        String firstName = Names.names().first().get();
        String lastName = Names.names().last().get();
        String password = Passwords.passwords().get();
        LocalDate dobStart = LocalDate.of(1950, 1, 1);
        LocalDate dobEnd = LocalDate.of(2000, 12, 31);
        Date birthday = Date.from(LocalDates.localDates().between(dobStart, dobEnd).get().atStartOfDay().toInstant(ZoneOffset.UTC));
        boolean newsletter = Bools.bools().get();
        boolean optIn = Bools.bools().get();
        getHomePage().getHeader().openAuthenticationPage();
        getAuthenticationPage().createAnAccount(email);
        getAuthenticationPage().fillRegistrationFormAndSubmit(title, firstName, lastName, password, birthday, newsletter, optIn);
        assertTrue(getAuthenticationPage().getAlert().contains("Your account has been created"), "Account is not created");
        log.info("Account created with the following arguments:");
        log.info("E-mail: " + email);
        log.info("Title: " + title);
        log.info("First name: " + firstName);
        log.info("Last name: " + lastName);
        log.info("Password: " + password);
        log.info("Birthday: " + birthday);
        log.info("Newsletter subscription: " + newsletter);
        log.info("Opt in: " + optIn);
    }

    @Test
    public void searchTest() {
    }

    @Test
    public void contactFormTest() {

    }

    @Test
    public void orderTest() {

    }
}
